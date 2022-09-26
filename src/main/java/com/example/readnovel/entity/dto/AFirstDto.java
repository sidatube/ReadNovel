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
    private long novelCount;
    private Timestamp createdAt;

    public AFirstDto(Author author) {
        id = author.getId();
        name=author.getName();
        createdAt= author.getCreatedAt();
        novelCount = author.getNovels().size();
    }
    public AFirstDto(Artist artist) {
        id = artist.getId();
        name=artist.getName();
        novelCount = artist.getNovels().size();
        createdAt= artist.getCreatedAt();
    }
}
