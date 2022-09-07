package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.HistoryItem;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryItemDto {
    private String novelId;
    private String novelName;
    private String novelAvatar;
    private String lastChapId;
    private String lastChapTitle;
    private String volumeName;

    public HistoryItemDto(HistoryItem historyItem) {
        if (historyItem.getLastChap()!=null){
            lastChapTitle = historyItem.getLastChap().getTitle();
            lastChapId=historyItem.getLastChap().getId();
            volumeName=historyItem.getLastChap().getVolume().getTitle();
        }
        if (historyItem.getNovel()!=null){
            novelId = historyItem.getNovel().getId();
            novelAvatar = historyItem.getNovel().getAvatar();
            novelName = historyItem.getNovel().getName();

        }
    }
}

