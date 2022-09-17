package com.example.readnovel.repository;

import com.example.readnovel.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<Account> findByUsername(String username);
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<Account> findByEmail(String email);
}

