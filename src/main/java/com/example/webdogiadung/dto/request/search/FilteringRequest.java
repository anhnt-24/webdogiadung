package com.example.webdogiadung.dto.request.search;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public abstract class FilteringRequest<T> {
    private PagingRequest paging = new PagingRequest();
    public abstract Specification<T> specification();
}