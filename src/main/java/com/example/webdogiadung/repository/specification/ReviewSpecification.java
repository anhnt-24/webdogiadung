package com.example.webdogiadung.repository.specification;

import com.example.webdogiadung.entity.ReviewEntity;
import org.springframework.util.ObjectUtils;

public class ReviewSpecification extends BaseSpecification<ReviewEntity> {
    private final String RATING = "rating";
    private final String CLIENT_NAME = "clientName";
    private final String PHONE = "phone";
    private final String PRODUCT = "product";
    private final String IS_DELETED = "isDeleted";

    public static ReviewSpecification builder() {
        return new ReviewSpecification();
    }

    public ReviewSpecification withRating(Double rating) {
        if (rating != null) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(RATING), rating));
        }
        return this;
    }

    public ReviewSpecification withClientName(String clientName) {
        if (!ObjectUtils.isEmpty(clientName)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(cb.lower(root.get(CLIENT_NAME)), "%" + clientName.toLowerCase() + "%"));
        }
        return this;
    }

    public ReviewSpecification withPhone(String phone) {
        if (!ObjectUtils.isEmpty(phone)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(PHONE), "%" + phone + "%"));
        }
        return this;
    }

    public ReviewSpecification withProductId(String productId) {
        if (!ObjectUtils.isEmpty(productId)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(PRODUCT).get("id"), productId));
        }
        return this;
    }

    public ReviewSpecification withIsDeleted(Boolean isDeleted) {
        if (!ObjectUtils.isEmpty(isDeleted)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(IS_DELETED), isDeleted));
        }
        return this;
    }
}
