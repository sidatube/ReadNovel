package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "members")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name",strategy = "com.example.readnovel.util.CustomId",parameters = @Parameter(name = "prefix",value = "MEMBER"))
    private String id;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}
            ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_roles_team",
            joinColumns =@JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    @JsonIgnoreProperties("members")
    private Set<RoleTeam> roles;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "accountId")
    @JsonIgnoreProperties("members")
    private Account account;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private TranslationTeam translationTeam;

    public void addRole(RoleTeam roleTeam){
        if (roles==null){
            roles = new HashSet<>();
        }
        roles.add(roleTeam);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(account, member.account) && Objects.equals(translationTeam, member.translationTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, translationTeam);
    }
}
