package com.example.readnovel.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDTO {

    private String accessToken;
    private String refreshToken;
    private AccountDTO account;

}
