package com.example.readnovel.repository;

import com.example.readnovel.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,String > {
    Optional<Chapter> findByTitle(String name);
}
