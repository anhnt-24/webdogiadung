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
@Table(name = "fact_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactStatus {
    @Id
    @Column(name = "status_key")
    private Integer statusKey;
    
    private String status;
    @Column(name = "total_orders")
    private Integer totalOrders;
    private BigDecimal  percentage;
    
    // Getters and setters
    // Constructors
}