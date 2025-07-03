package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.ProductDescriptionRequest;
import com.example.webdogiadung.dto.response.ProductDescriptionResponse;
import com.example.webdogiadung.entity.psql.ProductDescriptionEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductDescriptionMapper extends BaseEntityMapper<ProductDescriptionEntity, ProductDescriptionRequest, ProductDescriptionResponse> {

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ProductDescriptionEntity toEntity(ProductDescriptionRequest request);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateEntity(@MappingTarget ProductDescriptionEntity target, ProductDescriptionRequest source);
}
