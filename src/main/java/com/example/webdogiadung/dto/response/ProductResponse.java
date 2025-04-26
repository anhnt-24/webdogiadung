package com.example.webdogiadung.dto.response;

import com.example.webdogiadung.entity.CategoryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    String description;
    String brand;
    Long sellingPrice;
    String thumbnail;
    String categoryId;
    Long promotionPrice;
    Long sold;
    Long importPrice;
    Boolean isDeleted;
    Long stock;
    Double rating;

}
