package com.example.readnovel.config;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.dto.AccountDTO;
import com.example.readnovel.entity.dto.CredentialDTO;
import com.example.readnovel.entity.dto.RegisterDTO;
import com.example.readnovel.repository.AccountRepository;
import com.example.readnovel.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    public ApiAuthenticationFilter(AuthenticationManager authenticationManager,AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
    }
    //this function is call first when user try to login with their user name and password
    //so here we get username and password from request body then let spring do the magic
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            //it should be loginDTO
            RegisterDTO registerDTO = gson.fromJson(jsonData, RegisterDTO.class);
            String username = registerDTO.getUsername();
            String password = registerDTO.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
             e.printStackTrace();
             return null;
        }
    }

    //when username and password is correct this function will be call and pass in current login success information
    //so here we will return token for user
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal(); //get user that successfully login
        //generate tokens
        Account account = accountRepository.findByUsername(user.getUsername()).orElse(null);
        List<String > roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String accessToken = JwtUtil.generateToken(user.getUsername(),
                roles,
                request.getRequestURL().toString(),
                JwtUtil.ONE_DAY * 7);

        String refreshToken = JwtUtil.generateToken(user.getUsername(),
                roles,
                request.getRequestURL().toString(),
                JwtUtil.ONE_DAY * 14);

        CredentialDTO credential = new CredentialDTO(accessToken, refreshToken,new AccountDTO(account));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), credential);
    }
}
