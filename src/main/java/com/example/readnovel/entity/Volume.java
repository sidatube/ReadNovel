package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.example.readnovel.util.StringHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
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
    @ManyToOne()
    @JsonBackReference
    private Novel novel;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "volume")
    @OrderBy("number")
    private Set<Chapter> chapters;
    public String getSlug() {
        return StringHelper.toSlug(title, id);
    }

}
