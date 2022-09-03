package com.example.readnovel.auditorAware;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String username;
        try {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (Exception ignored){
            username = "System";
        }
        return Optional.of(username);
    }

}
