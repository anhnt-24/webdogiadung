package com.example.webdogiadung.repository.psql.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseSpecification <T>{
    protected List<Specification<T>> specifications=new ArrayList<Specification<T>>();
    public Specification<T> build(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.and(specifications.stream().filter(Objects::nonNull)
                .map(s->s.toPredicate(root,query,criteriaBuilder)).toArray(Predicate[]::new)));
    }
}
