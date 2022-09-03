package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "roles_team")
public class RoleTeam extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name",
            strategy = "com.example.readnovel.util.CustomId",
            parameters = @Parameter(name = "prefix",value = "ROLE"))
    private String id;
    private String name;
    @ManyToMany(mappedBy = "roles"
    )
    @JsonIgnoreProperties("roles")
    private Set<Member> members;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleTeam roleTeam = (RoleTeam) o;
        return Objects.equals(name, roleTeam.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
