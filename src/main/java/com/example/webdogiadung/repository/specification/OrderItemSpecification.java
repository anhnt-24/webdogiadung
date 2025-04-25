package com.example.webdogiadung.repository.specification;

import com.example.webdogiadung.entity.OrderItemEntity;
import org.springframework.util.ObjectUtils;

public class OrderItemSpecification extends BaseSpecification<OrderItemEntity>{
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String IS_DELETED = "isDeleted";

    public static OrderItemSpecification builder() {
        return new OrderItemSpecification();
    }

    public OrderItemSpecification withName(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(NAME), "%" + name + "%"));
        }
        return this;
    }

    public OrderItemSpecification withDescription(String description) {
        if (!ObjectUtils.isEmpty(description)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(DESCRIPTION), "%" + description + "%"));
        }
        return this;
    }

    public OrderItemSpecification withIsDeleted(Boolean isDeleted) {
        if (!ObjectUtils.isEmpty(isDeleted)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(IS_DELETED), isDeleted));
        }
        return this;

    }
}
