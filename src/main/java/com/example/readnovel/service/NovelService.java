package com.example.readnovel.service;

import com.example.readnovel.Filter.NovelFilter;
import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.constant.TranslationStatus;
import com.example.readnovel.constant.TypeOfStory;
import com.example.readnovel.criteriaFilter.NovelSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.*;
import com.example.readnovel.entity.dto.NovelDto;
import com.example.readnovel.entity.dto.NovelMinDto;
import com.example.readnovel.repository.*;
import com.example.readnovel.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NovelService {
    @Autowired
    private NovelRepository repository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TranslationTeamRepository translationTeamRepository;

    public Object save(NovelDto dto) throws CustomException {
        if (dto.getTypes() == null) {
            dto.setTypes(new ArrayList<>());
        }
        List<Type> types = typeRepository.findByNameIn(dto.getTypes());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account translator = accountRepository.findByUsername(username).orElse(null);
        Optional<Author> optionalAuthor = authorRepository.findByName(dto.getAuthor());
        Optional<Artist> optionalArtist = artistRepository.findByName(dto.getArtist());
        Artist artist;
        Author author;
        artist = optionalArtist.orElseGet(() -> artistRepository.save(Artist.builder().name(dto.getArtist()).build()));
        author = optionalAuthor.orElseGet(() -> authorRepository.save(Author.builder().name(dto.getAuthor()).build()));
        TranslationTeam translationTeam = translationTeamRepository.findByName(username).orElse(null);
        if (translator == null) {
            throw new CustomException("Username not found!");
        }
        if (translationTeam == null) {
            try {
                translationTeam = translationTeamRepository.findAll().get(0);
            } catch (Exception ignored) {

            }
        }
        Novel novel = Novel.builder()
                .types(types)
                .name(dto.getName())
                .artist(artist)
                .author(author)
                .avatar(dto.getAvatar())
                .extraNote(dto.getExtraNote())
                .sensitiveContent(dto.getSensitiveContent())
                .summary(dto.getSummary())
                .typeOfStory(dto.getTypeOfStory())
                .translationStatus(dto.getTranslationStatus())
                .otherName(dto.getOtherName())
                .translator(translator)
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .translationTeam(translationTeam)
                .build();
        if (novel.getName().isEmpty()) {
            throw new CustomException("Name id require");
        }
        Novel saved = repository.save(novel);
        return new NovelDto(saved);
    }

    public Object findById(String id) throws CustomException {

        return new NovelDto(findIdPrivate(id));
    }

    private Novel findIdPrivate(String id) throws CustomException {
        Novel novel = repository.findById(id).orElse(null);
        if (novel == null) {
            throw new CustomException("Novel not found!");
        }
        return novel;
    }

    public Object update(NovelDto newNovel) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Novel old = findIdPrivate(newNovel.getId());
        checkTranslation(username, old);
        Optional<Author> optionalAuthor = authorRepository.findByName(newNovel.getAuthor());
        Optional<Artist> optionalArtist = artistRepository.findByName(newNovel.getArtist());
        Artist artist;
        Author author;
        artist = optionalArtist.orElseGet(() -> artistRepository.save(Artist.builder().name(newNovel.getArtist()).build()));
        author = optionalAuthor.orElseGet(() -> authorRepository.save(Author.builder().name(newNovel.getAuthor()).build()));
        List<Type> types = typeRepository.findByNameIn(newNovel.getTypes());
        old.setName(newNovel.getName());
        old.setTypes(types);
        old.setArtist(artist);
        old.setAuthor(author);
        old.setAvatar(newNovel.getAvatar());
        old.setOtherName(newNovel.getOtherName());
        old.setExtraNote(newNovel.getExtraNote());
        old.setSensitiveContent(newNovel.getSensitiveContent());
        old.setSummary(newNovel.getSummary());
        old.setTypeOfStory(newNovel.getTypeOfStory());
        old.setTranslationStatus(newNovel.getTranslationStatus());
        old.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        return new NovelDto(repository.save(old));

    }

    private void checkTranslation(String username, Novel novel) throws CustomException {
        TranslationTeam team = novel.getTranslationTeam();
        if (team != null) {
            if (!team.isMember(username)) {
                throw new CustomException("Updater is not in translation team!");
            }
        } else {
            if (!novel.getTranslator().getUsername().equals(username)) {
                throw new CustomException("Updater is not translator!");
            }
        }
    }

    public boolean delete(String id) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Novel novel = findIdPrivate(id);
        checkTranslation(username, novel);
        repository.deleteById(id);
        return true;
    }

    public Page<Novel> getList(NovelFilter novelFilter) {
        Specification<Novel> specification = Specification.where(null);
        if (!(novelFilter.getName() == null || novelFilter.getName().isEmpty())) {
            String strFilter = StringHelper.removeAccent(novelFilter.getName());
            NovelSpecification nameFilter = new NovelSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, strFilter));
            NovelSpecification otherFilter = new NovelSpecification(new SearchCriteria("otherName", SearchCriteriaOperator.Like, strFilter));
            specification = specification.and(nameFilter).or(otherFilter);
        }
        if (!(novelFilter.getAuthor() == null || novelFilter.getAuthor().isEmpty())) {
            String strFilter = StringHelper.removeAccent(novelFilter.getAuthor());
            NovelSpecification author = new NovelSpecification(new SearchCriteria("author", SearchCriteriaOperator.Join, strFilter));
            specification = specification.and(author);
        }
        if (!(novelFilter.getArtist() == null || novelFilter.getArtist().isEmpty())) {
            String strFilter = StringHelper.removeAccent(novelFilter.getArtist());
            NovelSpecification artist = new NovelSpecification(new SearchCriteria("artist", SearchCriteriaOperator.Join, strFilter));
            specification = specification.and(artist);
        }
        if (!(novelFilter.getTypesId() == null || novelFilter.getTypesId().isEmpty())) {
            for (String str : novelFilter.getTypesId()
            ) {
                NovelSpecification typeIds = new NovelSpecification(new SearchCriteria("typeIds", SearchCriteriaOperator.Join, str));
                specification = specification.and(typeIds);
            }
        }
        if (!(novelFilter.getTypeOfStories() == null || novelFilter.getTypeOfStories().isEmpty())) {
            for (TypeOfStory type : novelFilter.getTypeOfStories()
            ) {
                NovelSpecification typeOfStory = new NovelSpecification(new SearchCriteria("typeOfStory", SearchCriteriaOperator.Equals, type));
                specification = specification.and(typeOfStory);
            }
        }
        if (!(novelFilter.getTranslationStatuses() == null || novelFilter.getTranslationStatuses().isEmpty())) {
            for (TranslationStatus translationStatus : novelFilter.getTranslationStatuses()
            ) {
                NovelSpecification status = new NovelSpecification(new SearchCriteria("translationStatus", SearchCriteriaOperator.Equals, translationStatus));
                specification = specification.and(status);
            }
        }
        Pageable pageable = PageRequest.of(novelFilter.getIndex() - 1, novelFilter.getSize(), Sort.by("lastUpdate").descending());
        return repository.findAll(specification, pageable);
    }

    public boolean adminDelete(String id) throws CustomException {
        Novel novel = findIdPrivate(id);
        repository.deleteById(id);
        return true;
    }

    public boolean followNovel(String id) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = findAccount(username);
        Novel novel = findIdPrivate(id);

        if (account.isFollow(id)) {
            account.unFollow(id);
            accountRepository.save(account);
            return false;
        }
        account.follow(novel);
        accountRepository.save(account);
        return true;
    }

    public Object getDetail(String id) throws CustomException {
        Novel find = findIdPrivate(id);
        find.setView(find.getView() + 1);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        NovelDto novelDto = new NovelDto(repository.save(find));
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getNovels() != null)
                novelDto.setFollow(account.isFollow(novelDto.getId()));
        }


        return novelDto;
    }

    public Page<Novel> getHot() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("view").descending());
        return repository.findAll(pageable);
    }

    private Account findAccount(String username) throws CustomException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent()) {
            throw new CustomException("user not found");
        }
        return optionalAccount.get();

    }

//    public Object getFollowList(int index, int size) throws CustomException {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Pageable pageable = PageRequest.of(index - 1, size, Sort.by("lastUpdate").descending().and(Sort.by("name")));
//        Page<Novel> novelPage = repository.findFollowByUsername(username, pageable);
//        return novelPage.map(NovelMinDto::new);
//    }

    public Object getFollowList(int index, int size) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Specification<Novel> specification = Specification.where(null);
        NovelSpecification followsFilter = new NovelSpecification(new SearchCriteria("follows", SearchCriteriaOperator.Join, username));
        specification = specification.and(followsFilter);
        Pageable pageable = PageRequest.of(index - 1, size, Sort.by("lastUpdate").descending().and(Sort.by("name")));
        Page<Novel> novelPage = repository.findAll(specification, pageable);
        return novelPage.map(NovelMinDto::new);
    }
}
