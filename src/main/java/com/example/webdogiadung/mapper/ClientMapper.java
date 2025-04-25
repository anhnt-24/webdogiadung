package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.ProductRequest;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.entity.ClientEntity;
import com.example.webdogiadung.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper extends BaseEntityMapper<ClientEntity, ClientRequest, ClientResponse>{

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    ClientEntity toEntity(ClientRequest clientRequest);

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEntity(@MappingTarget ClientEntity target, ClientRequest source);
}
