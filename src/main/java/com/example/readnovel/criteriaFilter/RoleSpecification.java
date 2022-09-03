package com.example.readnovel.criteriaFilter;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.Role;

import javax.persistence.criteria.*;
import java.util.Locale;

public class RoleSpecification extends GenericSpecification<Role>{
    public RoleSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (getSearchCriteria().getOperator() == SearchCriteriaOperator.Join) {
            if ("accounts".equals(getSearchCriteria().getKey().toLowerCase(Locale.ROOT))) {
                Join<Role, Account> tableJoin = root.join("accounts");
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
