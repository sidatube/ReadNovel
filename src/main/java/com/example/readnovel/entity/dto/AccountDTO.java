package com.example.readnovel.entity.dto;


import com.example.readnovel.constant.AccountStatusEnum;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountDTO {
    private String id;
    private String username;
    private List<String> roles;
    private String name;
    private String avatar;
    private String email;
    private Date dateOfBirth;
    private AccountStatusEnum status;
    private Timestamp createAt;

    public AccountDTO(Account account) {
        if (account == null) {
            return;
        }
        this.id = account.getId();
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.name = account.getName();
        this.avatar = account.getAvatar();
        this.dateOfBirth = account.getDateOfBirth();
        if (account.getRoles() != null)
            roles = account.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        this.status = account.getStatus();
        createAt=account.getCreatedAt();
    }
}