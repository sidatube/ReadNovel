package com.example.readnovel.Filter;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AFirstFilter {
    private String name;
    private String sortBy;
    private int index=1;
    private int size=10;

}
