package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Artist;
import com.example.readnovel.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AFirstDto {
    private String id;
    private String name="";
    private Timestamp createdAt;

    public AFirstDto(Author author) {
        id = author.getId();
        name=author.getName();
        createdAt= author.getCreatedAt();
    }
    public AFirstDto(Artist artist) {
        id = artist.getId();
        name=artist.getName();
        createdAt= artist.getCreatedAt();
    }
}
