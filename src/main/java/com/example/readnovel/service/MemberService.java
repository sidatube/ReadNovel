package com.example.readnovel.service;

import com.example.readnovel.entity.Artist;
import com.example.readnovel.entity.Member;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.repository.ArtistRepository;
import com.example.readnovel.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;
    public Member saveArtist(Member member){
            return repository.save(member);
    }
    public Member findById(String id){
        return repository.findById(id).orElse(null);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

}
