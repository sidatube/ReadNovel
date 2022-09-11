package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Novel;
import com.example.readnovel.util.ConvertFriendlyTime;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NovelMinDto {
    private String novelId;
    private String novelName;
    private String novelAvatar;
    private String lastChapId;
    private String lastChapTitle;
    private String volumeName;
    private Timestamp lastUpdate;
    private String friendlyTime;

    public NovelMinDto(Novel novel) {
        lastChapTitle = "Chương 0: Mở đầu";
        volumeName = "Tập 1";
        if (novel.getLastChapter() != null) {
            lastChapTitle =novel.getLastChapter().getNumberTitle()+": "+ novel.getLastChapter().getTitle();
            lastChapId = novel.getLastChapter().getId();
            volumeName = novel.getLastChapter().getVolume().getTitle();
        }
        novelId = novel.getId();
        novelAvatar = novel.getAvatar();
        novelName = novel.getName();
        lastUpdate = novel.getLastUpdate();
        friendlyTime = ConvertFriendlyTime.getFriendlyTime(lastUpdate);
    }
}
