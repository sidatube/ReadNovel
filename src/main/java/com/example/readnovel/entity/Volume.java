package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.example.readnovel.util.StringHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "volumes")
public class Volume extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name",strategy = "com.example.readnovel.util.CustomId",parameters = @Parameter(name = "prefix",value = "VOL"))
    private String id;
    private String title;
    private String thumbnail;
    private double number;
    @Column(updatable = false,insertable = false)
    private String novelId;
    @ManyToOne()
    @JoinColumn(name = "novelId")
    @JsonBackReference
    private Novel novel;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "volume")
    @OrderBy("number")
    @Where(clause = "is_deleted = false")
    @JsonIgnoreProperties("volume")
    private List<Chapter> chapters;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return Objects.equals(id, volume.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
