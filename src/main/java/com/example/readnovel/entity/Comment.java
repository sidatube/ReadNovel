package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
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
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private Comment parent;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private Comment grandpa;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE}, mappedBy = "grandpa")
    @OrderBy("createdAt")
    private List<Comment> child;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE}, mappedBy = "parent")
    @OrderBy("createdAt")
    private List<Comment> children;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Account account;
    private boolean isDeleted;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Novel novel;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Post post;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Chapter chapter;
}
