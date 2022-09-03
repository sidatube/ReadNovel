package com.example.readnovel.criteriaFilter;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.Role;
import com.example.readnovel.entity.Type;

import javax.persistence.criteria.*;
import java.util.Locale;

public class TypeSpecification extends GenericSpecification<Type>{
    public TypeSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Type> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return super.toPredicate(root, query, criteriaBuilder);
    }
}
