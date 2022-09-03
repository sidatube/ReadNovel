package com.example.readnovel.repository;

import com.example.readnovel.entity.Member;
import com.example.readnovel.entity.TranslationTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
}
