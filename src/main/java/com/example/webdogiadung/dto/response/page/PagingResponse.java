package com.example.webdogiadung.dto.response.page;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse<T> {
    List<T> contents = new ArrayList<>();
    PageableData paging;

    public PagingResponse<T> setContents(final List<T> contents) {
        this.contents = contents;
        return this;
    }

    public PagingResponse<T> setPaging(final PageableData paging) {
        this.paging = paging;
        return this;
    }
}