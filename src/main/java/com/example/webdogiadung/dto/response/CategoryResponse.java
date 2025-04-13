package com.example.webdogiadung.dto.response;

import com.example.webdogiadung.entity.Constants.CategoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponse {
    private String name;
    public static CategoryResponse fromCategory (CategoryEntity categoryEntity){
        return CategoryResponse.builder()
                .name(categoryEntity.getName())
                .build();
    }
}
