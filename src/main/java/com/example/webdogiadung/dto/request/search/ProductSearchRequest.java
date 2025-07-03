package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.psql.ProductEntity;
import com.example.webdogiadung.repository.psql.specification.ProductSpecification;
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
    Long minPrice;
    Long maxPrice;

    @Override
    public Specification<ProductEntity> specification() {
        return ProductSpecification.builder()
                .withCategoryId(categoryId)
                .withName(name)
                .withIsDeleted(isDeleted)
                .withPrice(minPrice, maxPrice)
                .build();
    }
}
