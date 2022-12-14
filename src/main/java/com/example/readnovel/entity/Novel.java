package com.example.readnovel.entity;

import com.example.readnovel.constant.TranslationStatus;
import com.example.readnovel.constant.TypeOfStory;
import com.example.readnovel.entity.base.BaseEntity;
import com.example.readnovel.util.StringHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "novels")
public class Novel extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId", parameters = @Parameter(name = "prefix", value = "NOVEL"))
    private String id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String otherName;
    @Column(columnDefinition = "TEXT")
    private String avatar;
    private String slug;
    private Boolean sensitiveContent =false;
    private TypeOfStory typeOfStory;
    @Column(columnDefinition = "TEXT")
    private String summary;
    @ManyToOne()
    @JsonBackReference
    private TranslationTeam translationTeam;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "novel_type", joinColumns = @JoinColumn(name = "novelId"), inverseJoinColumns = @JoinColumn(name = "typeId"))
    @OrderBy("name")
    @Where(clause = "is_deleted = false")
    @JsonManagedReference
    private List<Type> types;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH}, mappedBy = "novels")
    @JsonBackReference
    private List<Account> accounts;
    @Column(columnDefinition = "TEXT")
    private String extraNote;
    private TranslationStatus translationStatus;
    private Timestamp lastUpdate;
    private int view;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "novel")
    @JsonManagedReference
    @OrderBy("number")
    @Where(clause = "is_deleted = false")
    private List<Volume> volumes;
    @ManyToOne()
    @JsonBackReference
    private Account translator;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="authorId")
    @JsonManagedReference
    private Author author;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="artistId")
    @JsonManagedReference
    private Artist artist;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "novel")
    @JsonIgnore
    private List<Comment> comments;

    public String getSlug() {
        return StringHelper.toSlug(name, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Novel novel = (Novel) o;
        return Objects.equals(id, novel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
