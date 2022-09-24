package com.example.readnovel.service;

import com.example.readnovel.Filter.AccountFilter;
import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.AccountSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Role;
import com.example.readnovel.entity.dto.AccountDTO;
import com.example.readnovel.entity.dto.ChangePassword;
import com.example.readnovel.entity.dto.ForgotPassword;
import com.example.readnovel.repository.AccountRepository;
import com.example.readnovel.repository.NovelRepository;
import com.example.readnovel.repository.RoleRepository;
import com.example.readnovel.util.StringHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private NovelRepository novelRepository;


    public Page<Object> getList(AccountFilter filter) {
        Specification<Account> specification = Specification.where(null);
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            AccountSpecification name = new AccountSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, filter.getName()));
            specification = specification.and(name);
        }
        if (filter.getUsername() != null && !filter.getUsername().isEmpty()) {
            AccountSpecification username = new AccountSpecification(new SearchCriteria("username", SearchCriteriaOperator.Like, filter.getUsername()));
            specification = specification.and(username);
        }
        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            AccountSpecification email = new AccountSpecification(new SearchCriteria("email", SearchCriteriaOperator.Like, filter.getEmail()));
            specification = specification.and(email);
        }
        if (filter.getRoles() != null && !filter.getRoles().isEmpty()) {
            for (String str : filter.getRoles()
            ) {
                AccountSpecification role = new AccountSpecification(new SearchCriteria("join", SearchCriteriaOperator.Join, str));
                specification = specification.and(role);
            }
        }
        Sort sort = Sort.by("username");
        switch (filter.getSortBy()) {
            case "usernameDesc":
                sort = Sort.by("username").descending();
                break;
            case "name":
                sort = Sort.by("name");
                break;
            case "nameDesc":
                sort = Sort.by("name").descending();
                break;
            case "email":
                sort = Sort.by("email");
                break;
            case "emailDesc":
                sort = Sort.by("email").descending();
                break;
            default:
                break;
        }
        Pageable pageable = PageRequest.of(filter.getIndex() - 1, filter.getSize(),sort );
        Page<Account> page = accountRepository.findAll(specification, pageable);
        return page.map(AccountDTO::new);
    }

    public Account findById(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void AddToFollowList(String username, String novelId) {
        Account account = accountRepository.findByUsername(username).orElse(null);
        Novel novel = novelRepository.findById(novelId).orElse(null);
        if (account == null || novel == null) {
            throw new NullPointerException();
        }
        account.follow(novel);
        accountRepository.save(account);

    }

    public Object update(Account newItem) {
        Account old = findById(newItem.getId());
        if (old == null)
            throw new NullPointerException();
        old.setRoles(newItem.getRoles());
        old.setStatus(newItem.getStatus());
        old.setAvatar(newItem.getAvatar());
        old.setName(newItem.getName());
        old.setDateOfBirth(newItem.getDateOfBirth());
        old.setEmail(newItem.getEmail());
        return new AccountDTO(accountRepository.save(old));
    }

    private Pageable getPageable(int pageIndex, int pageSize) {
        try {
            return PageRequest.of(pageIndex - 1, pageSize);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean forgotPassword(ForgotPassword forgotPassword) {
        Account account = accountRepository.findByEmail(forgotPassword.getEmail()).orElse(null);
        return account != null;
    }

    public Object setNewPass(ChangePassword changePassword) throws CustomException {
        Account account = accountRepository.findByEmail(changePassword.getEmail()).orElse(null);

        if (!changePassword.getPassword().equals(changePassword.getRepeatPassword())) {
            StringHelper.customException("Password and repeat password are not same!");
        }
        account.setHashPass(passwordEncoder.encode(changePassword.getPassword()));
        accountRepository.save(account);
        return true;


    }

    public Object changePassword(String username, ChangePassword changePassword) throws CustomException {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account == null) {
            StringHelper.customException("Not Found Account");
        }
        String oldPass = changePassword.getOldPassword();
        if (!passwordEncoder.matches(oldPass, account.getHashPass())) {
            StringHelper.customException("Old password has Error!");
        }
        if (!changePassword.getPassword().equals(changePassword.getRepeatPassword())) {
            StringHelper.customException("Password and repeat password are not same!");
        }
        account.setHashPass(passwordEncoder.encode(changePassword.getPassword()));
        accountRepository.save(account);
        return true;


    }

    public boolean adminChangePassword(ChangePassword changePassword) throws CustomException {
        Account account = accountRepository.findByUsername(changePassword.getUsername()).orElse(null);
        if (account == null) {
            StringHelper.customException("Not Found Account");
        }
        if (!changePassword.getPassword().equals(changePassword.getRepeatPassword())) {
            StringHelper.customException("Password and repeat password are not same!");
        }
        account.setHashPass(passwordEncoder.encode(changePassword.getPassword()));
        accountRepository.save(account);
        return true;
    }

    public Object adminChangeInfo(AccountDTO newAcc) throws CustomException {
        Account account = accountRepository.findByUsername(newAcc.getUsername()).orElse(null);
        if (account == null) {
            StringHelper.customException("Not Found Account");
        }
        Optional<Account> accountOptional = accountRepository.findByEmail(newAcc.getEmail());
        if (accountOptional.isPresent()) {
            StringHelper.customException("Email had exit!");
        }
        Set<Role> roles = new HashSet<>(roleRepository.findByNameIn(newAcc.getRoles()));
        account.setStatus(newAcc.getStatus());
        account.setRoles(roles);
        account.setName(newAcc.getName());
        account.setAvatar(newAcc.getAvatar());
        account.setDateOfBirth(newAcc.getDateOfBirth());
        return new AccountDTO(accountRepository.save(account));

    }

    public Object findByUsername(String username) throws CustomException {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account == null) {
            StringHelper.customException("Not Found Account");
        }
        return new AccountDTO(account);
    }

    public Object changeInfo(String username, AccountDTO accountDTO) throws CustomException {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account == null) {
            StringHelper.customException("Not Found Account");
        }
        account.setEmail(accountDTO.getEmail());
        account.setName(accountDTO.getName());
        account.setAvatar(accountDTO.getAvatar());
        account.setDateOfBirth(accountDTO.getDateOfBirth());
        return new AccountDTO(accountRepository.save(account));
    }

    public Boolean deleteAccount(String id) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Account> optionalStaff = accountRepository.findByUsername(username);
        Optional<Account> optionalDeleted = accountRepository.findById(id);
        if (!optionalDeleted.isPresent()) {
            throw new CustomException("Account not Exist!");
        }
        Account staff = optionalStaff.get();
        Account deleted = optionalDeleted.get();
        if (staff.getUsername().contains(deleted.getUsername())) {
            throw new CustomException("Can't delete self");
        }
        if (staff.getRoles().stream().map(Role::getName).collect(Collectors.toList()).contains("admin")) {
            accountRepository.deleteById(id);
        } else {
            if (deleted.getRoles().stream().map(Role::getName).collect(Collectors.toList()).contains("admin")) {
                throw new CustomException("Khonog du quyen");
            } else
                accountRepository.deleteById(id);

        }
        return true;

    }
}
