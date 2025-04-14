package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.CategoryEntity;
import org.springframework.data.jpa.domain.Specification;

public class CategorySearchRequest extends FilteringRequest<CategoryEntity>{
    @Override
    public Specification<CategoryEntity> specification() {
        return null;
    }
}
