package com.example.readnovel.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.readnovel.constant.AccountStatusEnum;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.Role;
import com.example.readnovel.entity.dto.AccountDTO;
import com.example.readnovel.entity.dto.CredentialDTO;
import com.example.readnovel.entity.dto.RegisterDTO;
import com.example.readnovel.repository.AccountRepository;
import com.example.readnovel.repository.RoleRepository;
import com.example.readnovel.util.JwtUtil;
import com.example.readnovel.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    private static final String USER_ROLE = "user";

    // authorize là quyền, authentication là xác thực.
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("Username not exists!!");
        }
        Account account = optional.get();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : account.getRoles()
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(account.getUsername(), account.getHashPass(), authorities);
    }

    public AccountDTO saveAccount(RegisterDTO registerDTO) {
        //create new user role if not exist
        Optional<Role> userRoleOptional = roleRepository.findByName(USER_ROLE);
        Role userRole = userRoleOptional.orElse(null);
        if (userRole == null) {
            //create new role
            userRole = roleRepository.save(Role.builder().name(USER_ROLE).build());
        }
        if (registerDTO.getEmail().isEmpty()) {
            throw new DataIntegrityViolationException("Email is empty!");
        }
        if (!StringHelper.validateEmail(registerDTO.getEmail())) {

            throw new DataIntegrityViolationException("Validate Email!");

        }
        //check if username has exist
        Optional<Account> byUsername = accountRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()) {
            throw new DataIntegrityViolationException("username had exits");
        }
        Account account = new Account();

        account.setUsername(registerDTO.getUsername());
        account.setHashPass(passwordEncoder.encode(registerDTO.getPassword()));
        account.setStatus(AccountStatusEnum.ACTIVE);
        account.setEmail(registerDTO.getEmail());
        account.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        Account save = accountRepository.save(account);
        return new AccountDTO(save);
    }

    public Account getAccount(String username) {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        return byUsername.orElse(null);
    }

    public CredentialDTO refreshToken(String authorizationHeader, HttpServletRequest request) {
        String token = authorizationHeader.replace("Bearer", "").trim();
        DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(token);
        String username = decodedJWT.getSubject();
        //load account in the token
        Account account = getAccount(username);
        if (account == null) {
            return null;
        }
        //now return new token
        //generate tokens
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : account.getRoles()
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        }
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String accessToken = JwtUtil.generateToken(
                account.getUsername(),
                roles,
                request.getRequestURL().toString(),
                JwtUtil.ONE_DAY * 7);

        String refreshToken = JwtUtil.generateToken(
                account.getUsername(),
                roles,
                request.getRequestURL().toString(),
                JwtUtil.ONE_DAY * 14);

        return new CredentialDTO(accessToken, refreshToken, new AccountDTO(account));
    }
}
