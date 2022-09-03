package com.example.readnovel.repository;

import com.example.readnovel.entity.TranslationTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranslationTeamRepository extends JpaRepository<TranslationTeam,String> , JpaSpecificationExecutor<TranslationTeam> {
    Optional<TranslationTeam> findByName(String name);
}
