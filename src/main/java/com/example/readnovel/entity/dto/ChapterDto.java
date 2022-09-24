package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Chapter;
import com.example.readnovel.util.ConvertFriendlyTime;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterDto {
    private String id;
    private String title;
    private String content;
    private String volumeId;
    private String volumeTitle;
    private String numberTitle;
    private double number;
    private boolean isLock = false;
    private String slug;
    private int view;
    private String friendlyTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ChapterDto(Chapter chapter) {
        id = chapter.getId();
        title = chapter.getTitle();
        content = chapter.getContent();
        number = chapter.getNumber();
        numberTitle = chapter.getNumberTitle();
        volumeId = chapter.getVolume().getId();
        volumeTitle = chapter.getVolume().getTitle();
        isLock = chapter.isLock();
        view = chapter.getView();
        createdAt = chapter.getCreatedAt();
        updatedAt = chapter.getLastUpdate();
        friendlyTime = ConvertFriendlyTime.getFriendlyTime(updatedAt);

    }
}
