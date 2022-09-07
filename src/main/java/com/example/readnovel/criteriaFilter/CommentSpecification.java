package com.example.readnovel.criteriaFilter;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.entity.*;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Locale;

public class CommentSpecification extends GenericSpecification<Comment> {
    public CommentSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (getSearchCriteria().getOperator() == SearchCriteriaOperator.Join) {
            switch (getSearchCriteria().getKey().toLowerCase(Locale.ROOT)) {
                case "username":
                    Join<Comment, Account> commentAccountJoin = root.join("account");
                    return criteriaBuilder.or(
                            // tìm trong order bản ghi có id giống giá trị truyền vào
//                        criteriaBuilder.like(root.get("id"), "%" + getSearchCriteria().getValue() + "%"),
                            // hoặc tìm trong bảng product bản ghi có name giống với giá trị
                            criteriaBuilder.like(criteriaBuilder.lower(commentAccountJoin.get("username")), "%" + getSearchCriteria().getValue() + "%"));
                case "post":
                    Join<Comment, Post> post = root.join("post");

                    return criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(post.get("id")), "%" + getSearchCriteria().getValue() + "%")
                    );
                case "novel":
                    Join<Comment, Novel> novel = root.join("novel");
                    return criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(novel.get("id")), "%" + getSearchCriteria().getValue() + "%")
                    );
                case "chapter":
                    Join<Comment, Post> chapter = root.join("chapter");
                    return criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(chapter.get("id")), "%" + getSearchCriteria().getValue() + "%")
                    );
                default:
                    break;
            }

        }
        return super.toPredicate(root, query, criteriaBuilder);
    }
}
