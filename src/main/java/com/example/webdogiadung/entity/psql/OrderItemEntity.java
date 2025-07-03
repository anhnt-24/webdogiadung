package com.example.webdogiadung.entity.psql;


import com.example.webdogiadung.constants.OrderItemStatus;
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
    ProductEntity product;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    OrderItemStatus orderStatus;

}
