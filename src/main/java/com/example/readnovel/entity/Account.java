package com.example.readnovel.entity;

import com.example.readnovel.constant.AccountStatusEnum;
import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "accounts")
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId", parameters = @Parameter(name = "prefix", value = "USER"))
    private String id;
    @Column(unique = true)
    private String username;
    private String hashPass;
    private String name;
    private String avatar;
    @Column(unique = true)
    private String email;
    private Date dateOfBirth;
    private AccountStatusEnum status;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    @JsonIgnoreProperties("account")
    private Set<Role> roles;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinTable(
            name = "following",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "novelId")
    )
    @JsonManagedReference
    private Set<Novel> novels;//follows
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("account")
    private Set<Member> members;
    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties("account")
    private Set<Post> posts;
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "account")
    private HistoryRead historyRead;

    @OneToMany(mappedBy = "translator")

    private Set<Novel> listTranslationNovel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public Novel findNovel(String id) {
        Map<String, Novel> hashMap = novels.stream().collect(Collectors.toMap(Novel::getId, x -> x));
        if (hashMap.containsKey(id)) {
            return hashMap.get(id);
        }
        return null;
    }

    public boolean isFollow(String id) {
        Map<String, Novel> hashMap = novels.stream().collect(Collectors.toMap(Novel::getId, x -> x));
        return hashMap.containsKey(id);
    }

    public void follow(Novel novel) {
        novels.add(novel);
    }

    public void unFollow(String id) {
        Novel novel = findNovel(id);
        novels.remove(novel);
    }
}
