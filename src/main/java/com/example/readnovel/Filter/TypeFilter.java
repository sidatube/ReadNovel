package com.example.readnovel.Filter;

import com.example.readnovel.constant.TranslationStatus;
import com.example.readnovel.constant.TypeOfStory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TypeFilter {
    private String name;
    private int index=1;
    private int size=10;
    private String sortBy;
}
