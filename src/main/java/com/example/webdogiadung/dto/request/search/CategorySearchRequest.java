package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.psql.CategoryEntity;
import com.example.webdogiadung.repository.psql.specification.CategorySpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategorySearchRequest extends FilteringRequest<CategoryEntity>{
    String name;
    String description;
    Boolean isDeleted ;

    @Override
    public Specification<CategoryEntity> specification() {
        return CategorySpecification.builder()
                .withIsDeleted(isDeleted)
                .withName(name)
                .withDescription(description)
                .build();
    }
}
