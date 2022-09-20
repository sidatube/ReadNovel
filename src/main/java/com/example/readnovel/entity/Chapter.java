package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.example.readnovel.util.StringHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "chapters")
public class Chapter extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId", parameters = @Parameter(name = "prefix", value = "CHAP"))
    private String id;
    private String title;
    private String numberTitle;
    private double number;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne()
    @JsonIgnoreProperties("chapters")
    private Volume volume;
    private int view;
    private boolean isLock = false;
    private String slug;
    private Timestamp lastUpdate;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "chapter")
    @JsonBackReference
    private List<Comment> comments;

    public String getSlug() {
        return StringHelper.toSlug(title, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(Chapter newChap) {
        this.title = newChap.getTitle();
        this.content = newChap.getContent();
        this.volume = newChap.getVolume();
        this.view = newChap.getView();
        this.isLock = newChap.isLock();
    }


}
