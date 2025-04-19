package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSearchRequest extends FilteringRequest<ProductEntity>{
    @Override
    public Specification<ProductEntity> specification() {
        return null;
    }
}
