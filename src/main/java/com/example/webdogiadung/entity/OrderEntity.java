package com.example.webdogiadung.entity;

import com.example.webdogiadung.constants.MethodPayment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientEntity clientEntity;

    @Column(name = "order_code", nullable = false)
    String orderCode;

    @Column(name = "delivery_fee")
    Double deliveryFee;

    @Column(name = "total_amount")
    Double totalAmount;

    @Column(name = "is_active")
    Boolean isActive;
}
