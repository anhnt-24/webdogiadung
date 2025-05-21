package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.ReviewRequest;
import com.example.webdogiadung.dto.response.ReviewResponse;
import com.example.webdogiadung.entity.ReviewEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseEntityMapper<ReviewEntity, ReviewRequest, ReviewResponse> {

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ReviewEntity toEntity(ReviewRequest reviewRequest);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateEntity(@MappingTarget ReviewEntity target, ReviewRequest source);
}
