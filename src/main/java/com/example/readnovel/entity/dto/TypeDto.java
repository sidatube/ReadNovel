package com.example.readnovel.entity.dto;

import com.example.readnovel.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TypeDto {
    private String id;
    private String name;
    private String description;
    private Timestamp createdAt;

    public TypeDto(Type type) {
        id = type.getId();
        name = type.getName();
        description=type.getDescription();
        createdAt=type.getCreatedAt();
    }
}
