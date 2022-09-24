package com.example.readnovel.Filter;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountFilter {
    private String username;
    private String name;
    private String email;
    private List<String> roles;
    private String sortBy;
    private int index=1;
    private int size=10;

}
