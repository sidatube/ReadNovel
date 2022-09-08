package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Volume;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VolumeDto {
    private String id;
    private String title;
    private String novelId;
    private double number;
    private String thumbnail;
    private List<ChapterMinDto> chapters;

    public VolumeDto(Volume volume) {
        id = volume.getId();
        title = volume.getTitle();
        thumbnail = volume.getThumbnail();
        if (volume.getNovel() != null)
            novelId = volume.getNovel().getId();
        if (volume.getChapters() != null)
            chapters = volume.getChapters().stream().map(ChapterMinDto::new).collect(Collectors.toList());
    }
}
