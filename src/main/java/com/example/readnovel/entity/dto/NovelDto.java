package com.example.readnovel.entity.dto;

import com.example.readnovel.constant.TranslationStatus;
import com.example.readnovel.constant.TypeOfStory;
import com.example.readnovel.entity.Chapter;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Type;
import com.example.readnovel.entity.Volume;
import com.example.readnovel.util.ConvertFriendlyTime;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NovelDto {
    private String id;
    private String name;
    private String otherName;
    private String avatar;
    private String slug;
    private String author = "";
    private String artist = "";
    private List<VolumeDto> volumes;
    private Boolean sensitiveContent;
    private TypeOfStory typeOfStory;
    private String summary;
    private String translationTeam;
    private String extraNote;
    private TranslationStatus translationStatus;
    private int view;
    private int countFollow;
    private VolumeDto lastVol;
    private ChapterMinDto lastChapter;
    private Timestamp lastUpdate;
    private Timestamp createdAt;
    private boolean isFollow = false;
    private List<String> types = new ArrayList<>();
    private String friendlyTime;


    public NovelDto(Novel novel) {
        id = novel.getId();
        name = novel.getName();
        otherName = novel.getOtherName();
        avatar = novel.getAvatar();
        createdAt = novel.getCreatedAt();
        slug = novel.getSlug();
        if (novel.getAuthor() != null)
            author = novel.getAuthor().getName();
        if (novel.getArtist() != null)
            artist = novel.getArtist().getName();
        sensitiveContent = novel.getSensitiveContent();
        typeOfStory = novel.getTypeOfStory();
        summary = novel.getSummary();
        if (novel.getTranslationTeam() != null) {
            translationTeam = novel.getTranslationTeam().getName();
        }
        if (novel.getVolumes() != null) {
            volumes = novel.getVolumes().stream().map(VolumeDto::new).collect(Collectors.toList());
        }
        view = novel.getView();
        lastUpdate = novel.getLastUpdate();
        if (novel.getTypes() != null)
            types = novel.getTypes().stream().map(Type::getName).collect(Collectors.toList());
        if (!novel.getVolumes().isEmpty()) {
            List<Chapter> chapters = new ArrayList<>();
            for (Volume volume : novel.getVolumes()
            ) {
                chapters.addAll(volume.getChapters());
            }
            Chapter last = Collections.max(chapters, Comparator.comparing(Chapter::getCreatedAt));

            lastChapter = new ChapterMinDto(last);
            lastVol = new VolumeDto(last.getVolume());
        }

        if (novel.getAccounts() != null) {
            countFollow = novel.getAccounts().size();
        }
        friendlyTime = ConvertFriendlyTime.getFriendlyTime(lastUpdate);

    }
}
