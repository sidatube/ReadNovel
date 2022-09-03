package com.example.readnovel.service;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.criteriaFilter.TranslationTeamSpecification;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.*;
import com.example.readnovel.entity.dto.MemberDto;
import com.example.readnovel.entity.dto.TranslationTeamDto;
import com.example.readnovel.repository.AccountRepository;
import com.example.readnovel.repository.MemberRepository;
import com.example.readnovel.repository.RoleTeamRepository;
import com.example.readnovel.repository.TranslationTeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TranslationTeamService {
    @Autowired
    private TranslationTeamRepository repository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleTeamRepository roleTeamRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Object save(TranslationTeamDto translationTeamDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account == null) {
            throw new UsernameNotFoundException("Username not Exist!");
        }
        TranslationTeam translationTeam = TranslationTeam.builder().name(translationTeamDto.getName()).description(translationTeamDto.getDescription()).build();
        List<RoleTeam> roleTeams = roleTeamRepository.findAll();
        repository.save(translationTeam);
        Member member = Member.builder().account(account).translationTeam(translationTeam).roles(new HashSet<>(roleTeams)).build();
        member = memberRepository.save(member);
        translationTeam.addMember(member);
        return convertDTO(translationTeam);
    }

    public TranslationTeamDto convertDTO(TranslationTeam translationTeam) {
        TranslationTeamDto Dto = modelMapper.map(translationTeam, TranslationTeamDto.class);
        Set<Member> members = translationTeam.getMembers();
        Dto.setMembers(members.stream().map(MemberDto::new).collect(Collectors.toList()));
        return Dto;
    }

    public TranslationTeam findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean addMember(MemberDto memberDto) throws CustomException {
        TranslationTeam translationTeam = findById(memberDto.getTeamId());
        String invitorName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(memberDto.getUsername()).orElse(null);
        if (translationTeam == null || account == null) {
            throw new CustomException("Invalid Information!");
        }
        Member invitor = translationTeam.findMember(invitorName);
        if (invitor == null) {
            throw new CustomException("Error Information!");
        }
        if (translationTeam.findMember(account.getUsername()) != null) {
            throw new CustomException("Member Has Exist!");
        }
        List<String> roles = invitor.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList());
        if (!(roles.contains("admin") || roles.contains("mod"))) {
            throw new CustomException("Not qualified");
        }
        RoleTeam memberRole = roleTeamRepository.findByName("member").orElse(null);
        if (memberRole == null) {
            memberRole = roleTeamRepository.save(RoleTeam.builder().name("member").build());
        }
        Member newMem = Member.builder().account(account).translationTeam(translationTeam).build();
        newMem.addRole(memberRole);
        memberRepository.save(newMem);
        return true;

    }

    public Object update(TranslationTeamDto newItem) throws CustomException {
        TranslationTeam old = findById(newItem.getId());
        if (old == null) {
            throw new CustomException("Team not found");
        }
        old.setDescription(newItem.getDescription());
        old.setName(newItem.getName());
        return convertDTO(repository.save(old));

    }

    public boolean delete(String id) throws CustomException {
        TranslationTeam translationTeam = findById(id);
        if (translationTeam == null) {
            throw new CustomException("Team not found");
        }
        String setter = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(setter).orElse(null);
        if (account == null) {
            throw new CustomException("Account not found");
        }
        Member actor = translationTeam.findMember(setter);
        if (actor == null) {
            throw new CustomException("Member not found");
        }
        if (!actor.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList()).contains("admin")) {
            throw new CustomException("Error Information!");
        }
        repository.deleteById(id);
        return true;
    }
    private Pageable getPageable(int pageIndex, int pageSize) {
            return PageRequest.of(pageIndex-1, pageSize);
    }
    public Object findAll(String name,int index,int size) {
        Pageable sort = getPageable(index,size);
        Specification<TranslationTeam> specification = Specification.where(null);
        if (!name.isEmpty()) {
            TranslationTeamSpecification nameFilter = new TranslationTeamSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, name));
            specification = specification.and(nameFilter);
        }
        Page<TranslationTeam> translationTeams = repository.findAll(specification,sort);
        return translationTeams.map(this::convertDTO);
    }

    public boolean editMember(MemberDto memberDto) throws CustomException {
        TranslationTeam translationTeam = findById(memberDto.getTeamId());
        String setter = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(memberDto.getUsername()).orElse(null);
        List<String> noAdmin = new ArrayList<>();
        noAdmin.add("user");
        if (memberDto.getRoles() == null) {
            memberDto.setRoles(noAdmin);
        }

        if (translationTeam == null || account == null) {
            throw new CustomException("Invalid Information!");
        }
        Member actor = translationTeam.findMember(setter);
        if (actor == null) {
            throw new CustomException("Error Information!");
        }
        Member newMem = translationTeam.findMember(account.getUsername());
        if (newMem == null) {
            throw new CustomException("Member Hasn't Exist!");
        }

        List<String> actorRole = actor.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList());
        List<String> passiveRole = newMem.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList());
        roleLogic(actorRole, passiveRole, memberDto.getRoles());
        if (actorRole.contains("admin") && memberDto.getRoles().contains("admin")) {
            actor.getRoles().remove(RoleTeam.builder().name("admin").build());
            memberRepository.save(actor);
        }

        List<RoleTeam> roleTeams = roleTeamRepository.findByNameIn(memberDto.getRoles());
        newMem.setRoles(new HashSet<>(roleTeams));
        memberRepository.save(newMem);
        return true;
    }

    public boolean removeMember(MemberDto memberDto) throws CustomException {
        TranslationTeam translationTeam = findById(memberDto.getTeamId());
        String setter = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(memberDto.getUsername()).orElse(null);
        List<String> noAdmin = new ArrayList<>();
        noAdmin.add("user");
        if (memberDto.getRoles() == null) {
            memberDto.setRoles(noAdmin);
        }

        if (translationTeam == null || account == null) {
            throw new CustomException("Invalid Information!");
        }
        Member actor = translationTeam.findMember(setter);
        if (actor == null) {
            throw new CustomException("Error Information!");
        }
        Member newMem = translationTeam.findMember(account.getUsername());
        if (newMem == null) {
            throw new CustomException("Member Hasn't Exist!");
        }
        List<String> actorRole = actor.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList());
        List<String> passiveRole = newMem.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList());
        roleLogic(actorRole, passiveRole, memberDto.getRoles());
        memberRepository.deleteById(newMem.getId());
        return true;
    }

    private void roleLogic(List<String> actor, List<String> passive, List<String> change) throws CustomException {
        int maxActor = getMax(actor);
        int maxPassive = getMax(passive);
        int maxChange = getMax(change);
        if (maxActor < 1 || maxActor < maxPassive || maxActor < maxChange) {
            throw new CustomException("Not qualified");
        }
    }

    private int getMax(List<String> list) {
        int max = 0;
        for (String str : list
        ) {
            int turn = 0;
            switch (str) {
                case "mod":
                    turn = 1;
                    break;
                case "admin":
                    turn = 2;
                    break;
                default:
                    break;
            }
            if (turn >= max) {
                max = turn;
            }
        }
        return max;

    }

    public Object getDetail(String teamId) throws CustomException {
        TranslationTeam translationTeam = repository.findById(teamId).orElse(null);
        if (translationTeam == null) {
            throw new CustomException("Team not Found!");
        }
        return convertDTO(translationTeam);

    }
}
