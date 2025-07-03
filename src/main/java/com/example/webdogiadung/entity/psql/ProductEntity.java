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

    @Column(name = "brand")
    String brand;

    @Column(name = "selling_price")
    Long sellingPrice;

    String thumbnail;

    @Column(name = "promotion_price")
    Long promotionPrice;

    Long sold;

    @Column(name = "import_price")
    Long importPrice;

    @Column(name = "is_deleted")
    Boolean isDeleted=false;

    Long stock;
    Double rating;



}
