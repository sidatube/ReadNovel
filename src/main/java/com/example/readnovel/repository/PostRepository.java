package com.example.readnovel.repository;

import com.example.readnovel.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,String > {
    List<Post> findAllByTitleContaining(String name);
    Page<Post> findAllByTitleContaining(String name, Pageable pageable);
}
