package com.example.readnovel.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private NovelRepository novelRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<Object> getList(int index, int size) {
        Pageable sort = getPageable(index, size);
        Page<Account> page =accountRepository.findAll(sort);
        return page.map(AccountDTO::new);
    }
    public Account findById( String id){
        return accountRepository.findById(id).orElse(null);
    }
    public void AddToFollowList(String username,String novelId){
        Account account = accountRepository.findByUsername(username).orElse(null);
        Novel novel = novelRepository.findById(novelId).orElse(null);
        if (account==null||novel==null){
            throw new NullPointerException();
        }
        account.follow(novel);
        accountRepository.save(account);

    }
    public Object update(Account newItem){
        Account old = findById(newItem.getId());
        if (old==null)
            throw  new NullPointerException();
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
            return PageRequest.of(pageIndex-1, pageSize);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean forgotPassword(ForgotPassword forgotPassword) {
        Account account = accountRepository.findByUsername(forgotPassword.getUsername()).orElse(null);
        return account != null && forgotPassword.getEmail().equals(account.getEmail());
    }
    public Object setNewPass(ChangePassword changePassword) throws CustomException {
        Account account = accountRepository.findByUsername(changePassword.getUsername()).orElse(null);

        if (account==null||!account.getEmail().equals(changePassword.getEmail())){
            StringHelper.customException("information has error");
        }
        if (!changePassword.getPassword().equals(changePassword.getRepeatPassword())){
            StringHelper.customException("Password and repeat password are not same!");
        }
        account.setHashPass(passwordEncoder.encode(changePassword.getPassword()));
        accountRepository.save(account);
        return true;


    }
    public Object changePassword(String username,ChangePassword changePassword) throws CustomException {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account==null){
           StringHelper.customException("Not Found Account");
        }
        String oldPass = changePassword.getOldPassword();
        if(!passwordEncoder.matches(oldPass,account.getHashPass())){
            StringHelper.customException("Old password has Error!");
        }
        if (!changePassword.getPassword().equals(changePassword.getRepeatPassword())){
            StringHelper.customException("Password and repeat password are not same!");
        }
        account.setHashPass(passwordEncoder.encode(changePassword.getPassword()));
         accountRepository.save(account);
        return true;


    }

    public boolean adminChangePassword(ChangePassword changePassword) throws CustomException {
        Account account = accountRepository.findByUsername(changePassword.getUsername()).orElse(null);
        if (account==null){
            StringHelper.customException("Not Found Account");
        }
        if (!changePassword.getPassword().equals(changePassword.getRepeatPassword())){
            StringHelper.customException("Password and repeat password are not same!");
        }
        account.setHashPass(passwordEncoder.encode(changePassword.getPassword()));
        accountRepository.save(account);
        return true;
    }

    public Object adminChangeInfo(AccountDTO newAcc) throws CustomException {
        Account account = accountRepository.findByUsername(newAcc.getUsername()).orElse(null);
        if (account==null){
            StringHelper.customException("Not Found Account");
        }

        Set<Role> roles  = new HashSet<>( roleRepository.findByNameIn(newAcc.getRoles()));
        account.setStatus(newAcc.getStatus());
        account.setRoles(roles);
        account.setName(newAcc.getName());
        account.setAvatar(newAcc.getAvatar());
        account.setDateOfBirth(newAcc.getDateOfBirth());
        return new AccountDTO(accountRepository.save(account));

    }

    public Object findByUsername(String username) throws CustomException {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account==null){
            StringHelper.customException("Not Found Account");
        }
        return new AccountDTO(account);
    }

    public Object changeInfo(String username, AccountDTO accountDTO) throws CustomException {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account==null){
            StringHelper.customException("Not Found Account");
        }
        account.setEmail(accountDTO.getEmail());
        account.setName(accountDTO.getName());
        account.setAvatar(accountDTO.getAvatar());
        account.setDateOfBirth(accountDTO.getDateOfBirth());
        return new AccountDTO(accountRepository.save(account));
    }
}
