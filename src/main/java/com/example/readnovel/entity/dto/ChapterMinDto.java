package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Chapter;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterMinDto {
    private String id;
    private String title;
    private String volumeId;
    private boolean isLock =false;
    private int view;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ChapterMinDto(Chapter chapter) {
        id = chapter.getId();
        title=chapter.getNumberTitle()+": "+ chapter.getTitle();
        volumeId = chapter.getVolume().getId();
        isLock = chapter.isLock();
        view=chapter.getView();
        createdAt = chapter.getCreatedAt();
        updatedAt = chapter.getUpdatedAt();
    }
}
