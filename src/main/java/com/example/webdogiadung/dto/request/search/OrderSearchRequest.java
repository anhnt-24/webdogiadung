package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.ClientEntity;
import com.example.webdogiadung.entity.OrderEntity;
import com.example.webdogiadung.repository.specification.ClientSpecification;
import com.example.webdogiadung.repository.specification.OrderSpecification;
import org.springframework.data.jpa.domain.Specification;

public class OrderSearchRequest extends FilteringRequest<OrderEntity>{
    String name;
    String description;
    Boolean isDeleted ;

    @Override
    public Specification<OrderEntity> specification() {
        return OrderSpecification.builder()
                .withIsDeleted(isDeleted)
                .withName(name)
                .withDescription(description)
                .build();
    }
}
