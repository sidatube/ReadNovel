package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "historyItem")
public class HistoryItem extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "HI"))
    private String id;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "lastChapId")
    private Chapter lastChap;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "novelId")
    private Novel novel;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JsonBackReference
    private HistoryRead historyRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryItem that = (HistoryItem) o;
        return Objects.equals(novel, that.novel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(novel);
    }
}
