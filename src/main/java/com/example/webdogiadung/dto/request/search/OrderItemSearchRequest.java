package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.OrderItemEntity;
import com.example.webdogiadung.repository.specification.OrderItemSpecification;
import org.springframework.data.jpa.domain.Specification;

public class OrderItemSearchRequest extends FilteringRequest<OrderItemEntity>{

    @Override
    public Specification<OrderItemEntity> specification() {
        return OrderItemSpecification.builder()
                .build();
    }
}
