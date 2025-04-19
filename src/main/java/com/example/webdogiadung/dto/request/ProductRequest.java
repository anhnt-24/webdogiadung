package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
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
