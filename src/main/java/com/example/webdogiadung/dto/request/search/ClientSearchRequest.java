package com.example.webdogiadung.dto.request.search;


import com.example.webdogiadung.entity.psql.ClientEntity;
import com.example.webdogiadung.repository.psql.specification.ClientSpecification;
import org.springframework.data.jpa.domain.Specification;

public class ClientSearchRequest extends FilteringRequest<ClientEntity> {
    String name;
    String description;
    Boolean isDeleted ;

    @Override
    public Specification<ClientEntity> specification() {
        return ClientSpecification.builder()
                .withIsDeleted(isDeleted)
                .withName(name)
                .withDescription(description)
                .build();
    }
}
