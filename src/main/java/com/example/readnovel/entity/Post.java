package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "posts")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name",strategy = "com.example.readnovel.util.CustomId",parameters = @Parameter(name = "prefix",value = "POST"))
    private String id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne(cascade =CascadeType.ALL )
    @JsonIgnoreProperties("posts")
    private Account account;
    private boolean isDiscussion;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("posts")
    private TranslationTeam translationTeam;

}
