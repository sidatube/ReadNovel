package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Member;
import com.example.readnovel.entity.RoleTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberDto {
    private String memberId;
    private String username;
    private String teamId;
    private List<String> roles;
    private AccountDTO account;

    public MemberDto(Member member) {
        memberId = member.getId();
        username = member.getAccount().getUsername();
        teamId = member.getTranslationTeam().getId();
        if (member.getRoles() != null)
            roles = member.getRoles().stream().map(RoleTeam::getName).collect(Collectors.toList());
        account = new AccountDTO(member.getAccount());
    }
}
