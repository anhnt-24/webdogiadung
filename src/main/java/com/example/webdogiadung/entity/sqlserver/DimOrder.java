package com.example.webdogiadung.entity.sqlserver;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "dim_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimOrder {
    @Id
    @Column(name = "order_key")
    private Integer orderKey;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "order_item_id")
    private String orderItemId;
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;
    
    // Getters and setters
    // Constructors
}