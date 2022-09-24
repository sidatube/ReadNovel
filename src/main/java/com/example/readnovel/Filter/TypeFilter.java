package com.example.readnovel.Filter;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeFilter {
    private String name="";
    private int index=1;
    private int size=10;
    private String sortBy="";
}
