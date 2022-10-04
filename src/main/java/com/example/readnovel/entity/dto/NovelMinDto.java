package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Chapter;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Volume;
import com.example.readnovel.util.ConvertFriendlyTime;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    private boolean isFollow;
    private String volumeName;
    private Timestamp lastUpdate;
    private String friendlyTime;

    public NovelMinDto(Novel novel) {
        lastChapTitle = "Chương 0: Mở đầu";
        volumeName = "Tập 1";
        if (!novel.getVolumes().isEmpty()) {
            List<Chapter> chapters = new ArrayList<>();
            for (Volume volume : novel.getVolumes()
            ) {
                chapters.addAll(volume.getChapters());
            }

            if(!chapters.isEmpty()){
                Chapter last = Collections.max(chapters, Comparator.comparing(Chapter::getCreatedAt));
                lastChapTitle =last.getNumberTitle()+": "+ last.getTitle();
                lastChapId = last.getId();
                volumeName = last.getVolume().getTitle();
            }


        }

//        isFollow=novel.
        novelId = novel.getId();
        novelAvatar = novel.getAvatar();
        novelName = novel.getName();
        lastUpdate = novel.getLastUpdate();
        friendlyTime = ConvertFriendlyTime.getFriendlyTime(lastUpdate);
    }
}
