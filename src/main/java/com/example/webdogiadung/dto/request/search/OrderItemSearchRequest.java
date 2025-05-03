package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.OrderItemEntity;
import com.example.webdogiadung.repository.specification.OrderItemSpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemSearchRequest extends FilteringRequest<OrderItemEntity>{
    String orderId;
    @Override
    public Specification<OrderItemEntity> specification() {
        return OrderItemSpecification.builder()
                .withOrderCode(orderId)
                .build();
    }
}
