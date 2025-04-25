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
@Table(name = "order_items")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEntity extends BaseEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity productEntity;

    @Column(name = "")

    Long quantity;

    Long price;

    @Column(name = "is_active")
    Boolean isActive;
}
