package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.ProductEntity;
import com.example.webdogiadung.repository.specification.ProductSpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest extends FilteringRequest<ProductEntity>{

    String name;
    String categoryId;
    Boolean isDeleted;

    @Override
    public Specification<ProductEntity> specification() {
        return ProductSpecification.builder()
                .withCategoryId(categoryId)
                .withName(name)
                .withIsDeleted(isDeleted)
                .build();
    }
}
