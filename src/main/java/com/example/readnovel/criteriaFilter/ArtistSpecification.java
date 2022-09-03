package com.example.readnovel.criteriaFilter;

import com.example.readnovel.entity.Artist;
import com.example.readnovel.entity.Author;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ArtistSpecification extends GenericSpecification<Artist>{
    public ArtistSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Artist> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return super.toPredicate(root, query, criteriaBuilder);
    }
}
