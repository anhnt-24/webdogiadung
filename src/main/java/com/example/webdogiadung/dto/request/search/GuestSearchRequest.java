package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.psql.GuestEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuestSearchRequest extends FilteringRequest<GuestEntity> {
    @Override
    public Specification<GuestEntity> specification() {
        return null;
    }
}
