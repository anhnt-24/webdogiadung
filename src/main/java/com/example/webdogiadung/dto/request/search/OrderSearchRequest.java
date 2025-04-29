package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.ClientEntity;
import com.example.webdogiadung.entity.OrderEntity;
import com.example.webdogiadung.repository.specification.ClientSpecification;
import com.example.webdogiadung.repository.specification.OrderSpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderSearchRequest extends FilteringRequest<OrderEntity>{
    String orderCode;

    @Override
    public Specification<OrderEntity> specification() {
        return OrderSpecification.builder()
                .withOrderCode(orderCode)
                .build();
    }
}
