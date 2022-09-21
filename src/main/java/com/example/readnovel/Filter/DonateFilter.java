package com.example.readnovel.Filter;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonateFilter {
    private String username;
    private String code;
    private int index=1;
    private int size=20;
//    private Date startDate;
//    private Date endDate=new Date(System.currentTimeMillis());
}
