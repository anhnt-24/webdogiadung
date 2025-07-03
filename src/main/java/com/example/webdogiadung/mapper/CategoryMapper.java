package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.entity.psql.CategoryEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends  BaseEntityMapper<CategoryEntity,CategoryRequest, CategoryResponse>{

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    CategoryEntity toEntity(CategoryRequest request);

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEntity(@MappingTarget CategoryEntity target, CategoryRequest source);
}
