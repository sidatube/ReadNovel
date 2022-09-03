package com.example.readnovel.repository;

import com.example.readnovel.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String>, JpaSpecificationExecutor<Author> {
    List<Author> findAllByNameContains(String name);

    Optional<Author> findByName(String s);
}
