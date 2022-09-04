package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Chapter;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChapterDto {
    private String id;
    private String title;
    private String content;
    private String volumeId;
    private String number;
    private boolean isLock =false;
    private String slug;
    private int view;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ChapterDto(Chapter chapter) {
        id = chapter.getId();
        title= chapter.getTitle();
        content=chapter.getContent();
        number=chapter.getNumber();
        volumeId = chapter.getVolume().getId();
        isLock = chapter.isLock();
        view=chapter.getView();
        createdAt = chapter.getCreatedAt();
        updatedAt = chapter.getUpdatedAt();
    }
}
