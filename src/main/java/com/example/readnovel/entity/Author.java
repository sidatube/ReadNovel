package com.example.readnovel.entity;


import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "authors")
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name",strategy = "com.example.readnovel.util.CustomId",parameters = @Parameter(name = "prefix",value = "AUTHOR"))
    private String id;
    @Column(unique = true)
    private String name;
    private String otherName;
    @OneToMany( mappedBy = "author")
    @Where(clause = "is_deleted = false")
    @JsonBackReference
    private List<Novel> novels;

}
