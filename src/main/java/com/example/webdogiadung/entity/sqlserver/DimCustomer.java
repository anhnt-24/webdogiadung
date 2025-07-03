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
@Table(name = "dim_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimCustomer {
    @Id
    @Column(name = "customer_key")
    private Integer customerKey;
    @Column(name = "client_id")
    private String clientId;
    private String name;
    private String email;
    private String phone;
    private String address;
    @Column(name = "total_spent")
    private BigDecimal totalSpent;

    @Column(name = "num_order")
    private Integer numOrder;
}