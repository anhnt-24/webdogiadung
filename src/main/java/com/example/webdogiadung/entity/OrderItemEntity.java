package com.example.webdogiadung.entity;

import com.example.webdogiadung.entity.Constants.OrderStatus;

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

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "unit_price")
    Double unitPrice;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "delivery_address")
    String deliveryAddress;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    Boolean isDeleted=false;
}
