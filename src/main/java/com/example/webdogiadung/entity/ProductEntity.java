package com.example.webdogiadung.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String description;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    CategoryEntity category;
    String brand;
    Long sellingPrice;
    String thumbnail;
    Long promotionPrice;
    Long sold;
    Long importPrice;
    Boolean isDeleted=false;

}
