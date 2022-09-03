package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.TranslationTeam;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TranslationTeamDto {
    private String id;
    private String name;
    private String description;
    private List<MemberDto> members;
//    private List<NovelDto> novels;

}
