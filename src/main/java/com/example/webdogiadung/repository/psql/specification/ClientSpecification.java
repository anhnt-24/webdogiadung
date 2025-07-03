package com.example.webdogiadung.repository.psql.specification;

import com.example.webdogiadung.entity.psql.ClientEntity;
import org.springframework.util.ObjectUtils;

public class ClientSpecification  extends BaseSpecification<ClientEntity>{
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String IS_DELETED = "isDeleted";

    public static ClientSpecification builder() {
        return new ClientSpecification();
    }

    public ClientSpecification withName(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(NAME), "%" + name + "%"));
        }
        return this;
    }

    public ClientSpecification withDescription(String description) {
        if (!ObjectUtils.isEmpty(description)) {
            this.specifications.add((root, query, cb) ->
                    cb.like(root.get(DESCRIPTION), "%" + description + "%"));
        }
        return this;
    }

    public ClientSpecification withIsDeleted(Boolean isDeleted) {
        if (!ObjectUtils.isEmpty(isDeleted)) {
            this.specifications.add((root, query, cb) ->
                    cb.equal(root.get(IS_DELETED), isDeleted));
        }
        return this;

    }
}
