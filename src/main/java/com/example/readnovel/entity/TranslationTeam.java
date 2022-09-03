package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "translation_teams")
public class TranslationTeam extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId", parameters = @Parameter(name = "prefix", value = "TEAM"))
    private String id;
    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "translationTeam")
    @JsonManagedReference
    private Set<Member> members;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "translationTeam")
    @JsonManagedReference
    private Set<Novel> novels;

    public void addMember(Member member) {
        if (members == null) {
            members = new HashSet<>();
        }
        members.add(member);
    }

    public void addNovel(Novel novel) {
        if (novels == null) {
            novels = new HashSet<>();
        }
        novels.add(novel);
    }

    public Member findMember(String memberName) {
        Map<String,Member> hashMap = members.stream().collect(Collectors.toMap(x->x.getAccount().getUsername(), x->x));
        if (hashMap.containsKey(memberName)){
            return hashMap.get(memberName);
        }
        return null;
    }
    public boolean isMember(String memberName) {
        Map<String,Member> hashMap = members.stream().collect(Collectors.toMap(x->x.getAccount().getUsername(), x->x));
        return hashMap.containsKey(memberName);
    }
}
