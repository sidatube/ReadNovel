package com.example.readnovel.entity.group;

import com.example.readnovel.entity.DonateHistory;

import java.math.BigDecimal;
import java.util.List;

public interface DonateInMonth {
    BigDecimal getTotal();
    int getMonth();
}
