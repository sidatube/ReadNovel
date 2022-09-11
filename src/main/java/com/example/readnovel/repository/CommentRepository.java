package com.example.readnovel.repository;

import com.example.readnovel.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
    @Query("select u from comments  u where u.novel.id =:novelId and u.parent is null ")
    Page<Comment> findByNovelId(String novelId, Pageable pageable);

    @Query("select u from comments  u where u.chapter.id =:chapId and u.parent is null ")
    Page<Comment> findByChapterId(String chapId, Pageable pageable);
}
