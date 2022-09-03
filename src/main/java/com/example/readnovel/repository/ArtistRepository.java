package com.example.readnovel.repository;

import com.example.readnovel.entity.Artist;
import com.example.readnovel.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,String>, JpaSpecificationExecutor<Artist> {
    List<Artist> findByNameContaining(String name);

    Optional<Artist> findByName(String author);
}
