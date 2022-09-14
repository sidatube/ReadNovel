package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.HistoryItem;
import com.example.readnovel.util.ConvertFriendlyTime;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryItemDto {
    private String id;
    private String novelId;
    private String novelName;
    private String novelAvatar;
    private String lastChapId;
    private String lastChapTitle;
    private String volumeName;
    private Timestamp lastUpdate;
    private String friendlyTime;
    public HistoryItemDto(HistoryItem historyItem) {
        id = historyItem.getId();
        if (historyItem.getLastChap()!=null){
            lastChapTitle =historyItem.getLastChap().getNumberTitle()+" "+ historyItem.getLastChap().getTitle();
            lastChapId=historyItem.getLastChap().getId();
            volumeName=historyItem.getLastChap().getVolume().getTitle();
        }
        if (historyItem.getNovel()!=null){
            novelId = historyItem.getNovel().getId();
            novelAvatar = historyItem.getNovel().getAvatar();
            novelName = historyItem.getNovel().getName();

        }
        lastUpdate=historyItem.getUpdatedAt();
        friendlyTime= ConvertFriendlyTime.getFriendlyTime(lastUpdate);
    }
}

