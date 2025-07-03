package com.example.webdogiadung.entity.psql;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product_image")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage extends BaseEntity<String> {
    @Id
    String id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity productEntity;

    String name;
    String url;
}
