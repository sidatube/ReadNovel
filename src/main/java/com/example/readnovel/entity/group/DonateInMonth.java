package com.example.readnovel.entity.group;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonateInMonth {
    private BigDecimal total;
    private int month;
}
