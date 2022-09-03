package com.example.readnovel.criteriaFilter;

import com.example.readnovel.entity.Author;
import com.example.readnovel.entity.Type;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AuthorSpecification extends GenericSpecification<Author>{
    public AuthorSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return super.toPredicate(root, query, criteriaBuilder);
    }
}
