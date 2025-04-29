package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.entity.ClientEntity;
import com.example.webdogiadung.entity.OrderEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {ClientMapper.class})
public interface OrderMapper extends BaseEntityMapper<OrderEntity, OrderRequest, OrderResponse>{

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true),
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    OrderEntity toEntity(OrderRequest orderRequest);

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true),
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEntity(@MappingTarget OrderEntity target, OrderRequest source);
}
