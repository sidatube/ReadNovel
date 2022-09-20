package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.DonateHistory;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonateDto {
    private String id;
    private String username;
    private String code;
    private BigDecimal amount;
    private Timestamp createdAt;

    public DonateDto(DonateHistory history) {
        id = history.getId();
        username = history.getUsername();
        code = history.getCode();
        if (history.getAmount()!=null)
            amount=history.getAmount();
        createdAt=history.getCreatedAt();
    }
}
