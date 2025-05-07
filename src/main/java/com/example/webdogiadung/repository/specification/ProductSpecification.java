package com.example.webdogiadung.repository.specification;

import com.example.webdogiadung.entity.ProductEntity;
import org.springframework.util.ObjectUtils;

public class ProductSpecification extends BaseSpecification<ProductEntity> {
    private final String NAME = "name";
    private final String CATEGORY = "category";
    private final String IS_DELETED = "isDeleted";
    private final String PRICE = "promotionPrice";

    public static ProductSpecification builder() {
        return new ProductSpecification();
    }

    public ProductSpecification withName(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(cb.lower(root.get(NAME)), "%" + name.toLowerCase() + "%"));
        }
        return this;
    }

    public ProductSpecification withCategoryId(String categoryId) {
        if (!ObjectUtils.isEmpty(categoryId)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(CATEGORY).get("id"), categoryId));
        }
        return this;
    }
    public ProductSpecification withIsDeleted(Boolean isDeleted) {
        if (!ObjectUtils.isEmpty(isDeleted)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(IS_DELETED), isDeleted));
        }
        return this;
    }
    public ProductSpecification withPrice(Long minPrice, Long maxPrice) {
        if (minPrice != null || maxPrice != null) {
            if (minPrice != null && maxPrice != null) {
                this.specifications.add((root, query, cb) ->
                        cb.between(root.get(PRICE), minPrice, maxPrice));
            } else if (minPrice != null) {
                this.specifications.add((root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get(PRICE), minPrice));
            } else if (maxPrice != null) {
                this.specifications.add((root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get(PRICE), maxPrice));
            }
        }
        return this;
    }
}
