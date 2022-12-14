package com.example.readnovel.criteriaFilter;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.entity.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NovelSpecification extends GenericSpecification<Novel> {
    public NovelSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Novel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (getSearchCriteria().getOperator() == SearchCriteriaOperator.Join) {
            switch (getSearchCriteria().getKey().toLowerCase(Locale.ROOT)) {
                case "author":
                    Join<Novel, Author> authorNovelJoin = root.join("author");
                    return criteriaBuilder.or(
                            // tìm trong order bản ghi có id giống giá trị truyền vào
//                        criteriaBuilder.like(root.get("id"), "%" + getSearchCriteria().getValue() + "%"),
                            // hoặc tìm trong bảng product bản ghi có name giống với giá trị
                            criteriaBuilder.like(criteriaBuilder.lower(authorNovelJoin.get("name")), "%" + getSearchCriteria().getValue().toString().toLowerCase() + "%"));
                case "is_author_del":
                    Join<Novel, Author> author = root.join("author",JoinType.LEFT);
                    return criteriaBuilder.or(
                            // tìm trong order bản ghi có id giống giá trị truyền vào
//                        criteriaBuilder.like(root.get("id"), "%" + getSearchCriteria().getValue() + "%"),
                            // hoặc tìm trong bảng product bản ghi có name giống với giá trị
                            criteriaBuilder.notEqual(author.get("isDeleted"), true),criteriaBuilder.isNull(author));
                case "artist":
                    Join<Artist, Novel> artistJoin = root.join("artist");
                    return criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(artistJoin.get("name")), "%" + getSearchCriteria().getValue().toString().toLowerCase() + "%"));
                case "typeids":
                    Join<Novel,Type> novelTypeJoin = root.join("types");
                    return criteriaBuilder.equal(novelTypeJoin.get("name"),getSearchCriteria().getValue());
//                    query.distinct(true);
//                    Root<Type> typeRoot = query.from(Type.class);
//                    Expression<Collection<Novel>> novelTypes = typeRoot.get("novels");
//                    return criteriaBuilder.and(
//                            criteriaBuilder.equal(typeRoot.get("name"), getSearchCriteria().getValue()),
//                            criteriaBuilder.isMember(root, novelTypes));
                case "follows":
                    Join<Novel,Account> novelAccountJoin = root.join("accounts");
                    return criteriaBuilder.equal(novelAccountJoin.get("username"),getSearchCriteria().getValue());
//                    query.distinct(true);
//                    Root<Account> accountRoot = query.from(Account.class);
//                    Expression<Collection<Novel>> followsNovel = accountRoot.get("novels");
//                    return criteriaBuilder.and(
//                            criteriaBuilder.equal(accountRoot.get("username"), getSearchCriteria().getValue()),
//                            criteriaBuilder.isMember(root, followsNovel));

                default:
                    break;
            }

        }
        return super.toPredicate(root, query, criteriaBuilder);
    }
}
