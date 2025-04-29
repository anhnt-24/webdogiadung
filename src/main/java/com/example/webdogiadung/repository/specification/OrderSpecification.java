package com.example.webdogiadung.repository.specification;

import com.example.webdogiadung.entity.OrderEntity;
import org.springframework.util.ObjectUtils;

public class OrderSpecification extends BaseSpecification<OrderEntity>{
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String IS_DELETED = "isDeleted";
    private final String ORDER_CODE = "orderCode";
    public static OrderSpecification builder() {
        return new OrderSpecification();
    }

    public OrderSpecification withName(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(NAME), "%" + name + "%"));
        }
        return this;
    }

    public OrderSpecification withDescription(String description) {
        if (!ObjectUtils.isEmpty(description)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(DESCRIPTION), "%" + description + "%"));
        }
        return this;
    }

    public OrderSpecification withIsDeleted(Boolean isDeleted) {
        if (!ObjectUtils.isEmpty(isDeleted)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(IS_DELETED), isDeleted));
        }
        return this;

    }
    public OrderSpecification withOrderCode(String orderCode) {
        if (!ObjectUtils.isEmpty(orderCode)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(ORDER_CODE), "%" + orderCode + "%"));
        }
        return this;
    }

}
