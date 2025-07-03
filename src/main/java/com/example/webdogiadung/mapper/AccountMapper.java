package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.AccountRequest;
import com.example.webdogiadung.dto.response.AccountResponse;
import com.example.webdogiadung.entity.psql.AccountEntity;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseEntityMapper<AccountEntity, AccountRequest, AccountResponse> {
    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    AccountEntity toEntity(AccountRequest accountRequest);

    @Override
    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEntity(@MappingTarget AccountEntity target, AccountRequest source);
}
