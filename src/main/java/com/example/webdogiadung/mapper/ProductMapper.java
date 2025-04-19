package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.ProductRequest;
import com.example.webdogiadung.dto.response.ProductResponse;
import com.example.webdogiadung.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseEntityMapper<ProductEntity, ProductRequest, ProductResponse>{
    @Override

    @Mappings({
            @Mapping(target = "thumbnail",ignore = true),
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    ProductEntity toEntity(ProductRequest productRequest);

    @Override
    @Mappings({
            @Mapping(target = "thumbnail",ignore = true),
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEntity(@MappingTarget ProductEntity target, ProductRequest source);
}
