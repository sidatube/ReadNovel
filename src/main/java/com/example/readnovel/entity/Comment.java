package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import javafx.geometry.Pos;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId", parameters = @Parameter(name = "prefix", value = "CMT"))
    private String id;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Comment parent;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH}, mappedBy = "parent")
    private List<Comment> child;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Account account;
    private boolean isDeleted;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Novel novel;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Post post;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Chapter chapter;
}
