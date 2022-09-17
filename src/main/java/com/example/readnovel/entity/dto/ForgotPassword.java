package com.example.readnovel.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ForgotPassword {
    private String username;
    private String email;
}
