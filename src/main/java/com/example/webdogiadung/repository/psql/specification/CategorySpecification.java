package com.example.webdogiadung.repository.psql.specification;

import com.example.webdogiadung.entity.psql.CategoryEntity;
import org.springframework.util.ObjectUtils;

public class CategorySpecification extends BaseSpecification<CategoryEntity>  {
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String IS_DELETED = "isDeleted";

    public static CategorySpecification builder() {
        return new CategorySpecification();
    }

    public CategorySpecification withName(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(cb.lower(root.get(NAME)), "%" + name.toLowerCase() + "%"));
        }
        return this;
    }


    public CategorySpecification withDescription(String description) {
        if (!ObjectUtils.isEmpty(description)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(DESCRIPTION), "%" + description + "%"));
        }
        return this;
    }

    public CategorySpecification withIsDeleted(Boolean isDeleted) {
        if (!ObjectUtils.isEmpty(isDeleted)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(IS_DELETED), isDeleted));
        }
        return this;

    }
}
