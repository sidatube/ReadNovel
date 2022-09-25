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
            switch (getSearchCriteria().getKey().toLowerCase(Locale.ROOT)) {

                case "roles":
                    Join<Account, Role> join = root.join("roles");
                    return criteriaBuilder.equal(join.get("name"), getSearchCriteria().getValue());
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
