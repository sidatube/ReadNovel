package com.example.readnovel.criteriaFilter;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.Member;
import com.example.readnovel.entity.Role;
import com.example.readnovel.entity.TranslationTeam;

import javax.persistence.criteria.*;
import java.util.Locale;

public class TranslationTeamSpecification extends GenericSpecification<TranslationTeam>{
    public TranslationTeamSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<TranslationTeam> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (getSearchCriteria().getOperator() == SearchCriteriaOperator.Join) {
            if ("members".equals(getSearchCriteria().getKey().toLowerCase(Locale.ROOT))) {
                Join<TranslationTeam, Member> tableJoin = root.join("members");
                return criteriaBuilder.or(
                        // tìm trong order bản ghi có id giống giá trị truyền vào
//                        criteriaBuilder.like(root.get("id"), "%" + getSearchCriteria().getValue() + "%"),
                        // hoặc tìm trong bảng product bản ghi có name giống với giá trị
                        criteriaBuilder.like(criteriaBuilder.lower(tableJoin.get("name")), "%" + getSearchCriteria().getValue() + "%"));
            }
        }
        return super.toPredicate(root, query, criteriaBuilder);
    }
}
