package com.example.readnovel.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword extends ForgotPassword {
    private String oldPassword;
    private String password;
    private String repeatPassword;

}
