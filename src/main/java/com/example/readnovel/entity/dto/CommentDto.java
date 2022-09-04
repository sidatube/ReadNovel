package com.example.readnovel.entity.dto;

import com.example.readnovel.constant.AreaEnum;
import com.example.readnovel.entity.Comment;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String id;
    private String parentId;
    private List<String> childIds;
    private String content;
    private String userName;
    private boolean isDeleted;
    private AreaEnum areaEnum;
    private String areaId;
    private Timestamp updatedAt;
    private String updatedBy;

    public CommentDto(Comment comment) {
        id = comment.getId();
        parentId = comment.getParent().getId();
        if (comment.getChild() != null)
            childIds = comment.getChild().stream().map(Comment::getId).collect(Collectors.toList());
        content = comment.getContent();
        updatedAt = comment.getUpdatedAt();
        userName = comment.getAccount().getUsername();
        isDeleted = comment.isDeleted();
        if (comment.getNovel() != null) {
            areaEnum = AreaEnum.NOVEL;
            areaId = comment.getNovel().getId();
        }

        if (comment.getPost() != null) {
            areaEnum = AreaEnum.POST;
            areaId = comment.getPost().getId();
        }
        if (comment.getChapter() != null) {
            areaEnum = AreaEnum.CHAPTER;
            areaId = comment.getChapter().getId();
        }
        updatedBy = comment.getUpdatedBy();
    }
}
