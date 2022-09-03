package com.example.readnovel.service;

import com.example.readnovel.entity.Artist;
import com.example.readnovel.entity.Comment;
import com.example.readnovel.repository.ArtistRepository;
import com.example.readnovel.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;
    public Comment saveArtist(Comment comment){
        if (!comment.getContent().isEmpty()){
            return repository.save(comment);
        }
        throw new NullPointerException();
    }
   public Comment findById(String id){
        return repository.findById(id).orElse(null);
   }
   public Comment update(Comment comment){
        Comment old = findById(comment.getId());
        if (old==null||comment.isDeleted())
            throw new NullPointerException();
        old.setContent(comment.getContent());
        return repository.save(old);
   }
   public void delete(String id){
        Comment comment =findById(id);
       if (comment==null||comment.isDeleted())
           throw new NullPointerException();
       comment.setDeleted(true);

   }
}
