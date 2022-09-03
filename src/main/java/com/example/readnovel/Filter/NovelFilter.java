package com.example.readnovel.Filter;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NovelFilter {
    private String name;
    private List<String> typesId;
    private String author;
    private String artist;
    private int index=1;
    private int size=20;

}
