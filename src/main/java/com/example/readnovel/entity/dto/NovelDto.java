package com.example.readnovel.entity.dto;

import com.example.readnovel.constant.TranslationStatus;
import com.example.readnovel.constant.TypeOfStory;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Type;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NovelDto {
    private String id;
    private String name;
    private String otherName;
    private String avatar;
    private String slug;
    private String author;
    private String artist;
    private List<VolumeDto> volumes;
    private Boolean sensitiveContent;
    private TypeOfStory typeOfStory;
    private String summary;
    private String translationTeam;
    private String extraNote;
    private TranslationStatus translationStatus;
    private int view;
    private List<String> types;

    public NovelDto(Novel novel) {
        id = novel.getId();
        name=novel.getName();
        otherName=novel.getOtherName();
        avatar=novel.getAvatar();
        slug=novel.getSlug();
        author=novel.getAuthor().getName();
        artist=novel.getArtist().getName();
        sensitiveContent=novel.getSensitiveContent();
        typeOfStory=novel.getTypeOfStory();
        summary=novel.getSummary();
        if (novel.getTranslationTeam()!=null){
            translationTeam=novel.getTranslationTeam().getName();
        }
        if (novel.getVolumes()!=null){
            volumes=novel.getVolumes().stream().map(VolumeDto::new).collect(Collectors.toList());
        }
        view=novel.getView();
        types=novel.getTypes().stream().map(Type::getName).collect(Collectors.toList());
    }
}
