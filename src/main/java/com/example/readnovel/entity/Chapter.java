package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.example.readnovel.util.StringHelper;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

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
    @GenericGenerator(name = "custom-name",strategy = "com.example.readnovel.util.CustomId",parameters = @Parameter(name = "prefix",value = "CHAP"))
    private String id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne()
    private Volume volume;
    private int view;
    private boolean isLock= false;
    private String slug;
    public String getSlug() {
        return StringHelper.toSlug(title, id);
    }

    public void update(Chapter newChap){
        this.title = newChap.getTitle();
        this.content = newChap.getContent();
        this.volume = newChap.getVolume();
        this.view = newChap.getView();
        this.isLock = newChap.isLock();
    }





}
