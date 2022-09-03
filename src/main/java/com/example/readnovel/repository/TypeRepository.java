package com.example.readnovel.repository;

import com.example.readnovel.entity.TranslationTeam;
import com.example.readnovel.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type,String>, JpaSpecificationExecutor<Type> {
    Optional<Type> findByName(String name);
    List<Type> findByIdIn(Collection<String >ids);
    List<Type> findByNameIn(Collection<String >names);
}
