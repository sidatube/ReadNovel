package com.example.readnovel.service;

import com.example.readnovel.entity.Member;
import com.example.readnovel.entity.Post;
import com.example.readnovel.repository.MemberRepository;
import com.example.readnovel.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post saveArtist(Post post) {
        return repository.save(post);
    }

    public Post findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Post Update(Post newPost) {
        Post old = findById(newPost.getId());
        if (old == null) {
            throw new NullPointerException();
        }
        old.setTitle(newPost.getTitle());
        old.setContent(newPost.getContent());

        return repository.save(old);

    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
