package com.example.webdogiadung.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDescriptionRequest {
    String id;
    String description;
    String productId;
}
