package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.OrderItemRequest;
import com.example.webdogiadung.dto.response.OrderItemResponse;
import com.example.webdogiadung.entity.psql.OrderItemEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends BaseEntityMapper<OrderItemEntity, OrderItemRequest, OrderItemResponse>{

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true),
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    OrderItemEntity toEntity(OrderItemRequest orderItemRequest);

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEntity(@MappingTarget OrderItemEntity target, OrderItemRequest source);
}
