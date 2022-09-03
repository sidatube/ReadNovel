package com.example.readnovel.repository;

import com.example.readnovel.entity.Author;
import com.example.readnovel.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
}
