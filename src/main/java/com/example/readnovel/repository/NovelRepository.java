package com.example.readnovel.repository;

import com.example.readnovel.entity.Novel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface NovelRepository extends JpaRepository<Novel,String >, JpaSpecificationExecutor<Novel> {


}
