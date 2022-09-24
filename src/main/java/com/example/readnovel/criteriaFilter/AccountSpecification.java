package com.example.readnovel.criteriaFilter;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.entity.*;

import javax.persistence.criteria.*;
import java.util.Locale;

public class AccountSpecification extends GenericSpecification<Account> {
    public AccountSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (getSearchCriteria().getOperator() == SearchCriteriaOperator.Join) {
            if ("roles".equals(getSearchCriteria().getKey().toLowerCase(Locale.ROOT))) {
                Join<Account, Role> novelAccountJoin = root.join("roles");
                return criteriaBuilder.equal(novelAccountJoin.get("name"), getSearchCriteria().getValue());
            }

        }
        return super.toPredicate(root, query, criteriaBuilder);
    }
}
