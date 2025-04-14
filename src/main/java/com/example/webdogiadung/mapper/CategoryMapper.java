package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends  BaseEntityMapper<CategoryEntity,CategoryRequest, CategoryResponse>{

    @Override
    @Mappings({
            @Mapping(target = "thumbnail",ignore = true),
            @Mapping(target = "id",ignore = true)
    })
    CategoryEntity toEntity(CategoryRequest request);

    @Override
    @Mappings({
            @Mapping(target = "thumbnail",ignore = true),
            @Mapping(target = "id",ignore = true)
    })
    void updateEntity(@MappingTarget CategoryEntity target, CategoryRequest source);
}
