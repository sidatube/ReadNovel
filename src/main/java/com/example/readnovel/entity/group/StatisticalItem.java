package com.example.readnovel.entity.group;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticalItem {
    private List<DonateInMonth> donateInMonth;
    private int userCount;
    private int novelCount;
}
