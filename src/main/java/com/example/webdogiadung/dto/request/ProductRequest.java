package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.entity.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String id;

    String name;

    String description;

    String categoryId;

    String brand;

    Long sellingPrice;

    MultipartFile thumbnail;

    Long promotionPrice;

    Long sold;

    Long importPrice;

    Boolean isDeleted;
}
