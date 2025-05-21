package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.ReviewEntity;
import com.example.webdogiadung.repository.specification.ReviewSpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewSearchRequest extends FilteringRequest<ReviewEntity> {
    Double rating;
    String clientName;
    String phone;
    String productId;
    Boolean isDeleted;

    @Override
    public Specification<ReviewEntity> specification() {
        return ReviewSpecification.builder()
                .withRating(rating)
                .withClientName(clientName)
                .withPhone(phone)
                .withProductId(productId)
                .withIsDeleted(isDeleted)
                .build();
    }
}
