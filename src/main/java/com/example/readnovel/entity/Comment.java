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
    @ManyToOne(cascade = CascadeType.ALL)
    private Comment parent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Comment> child;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;
    private boolean isDeleted;
    @ManyToOne(cascade = CascadeType.ALL)
    private Novel novel;
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;
    @ManyToOne(cascade = CascadeType.ALL)
    private Chapter chapter;
}
