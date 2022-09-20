package com.example.readnovel.criteriaFilter;

import com.example.readnovel.entity.DonateHistory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DonateSpecification extends GenericSpecification<DonateHistory>{
    public DonateSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<DonateHistory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return super.toPredicate(root, query, criteriaBuilder);
    }
}
