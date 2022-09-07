package com.example.readnovel.Filter;

import com.example.readnovel.constant.AreaEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentFilter {
    private String username;
    private AreaEnum areaEnum =AreaEnum.ALL;
    private String  areaId;
    private String content;
    private int index=1;
    private int size=20;

}
