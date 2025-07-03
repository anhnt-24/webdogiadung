package com.example.webdogiadung.entity.sqlserver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "fact_sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactSales {
    @Id
    @Column(name = "sales_key")
    private Integer salesKey;
    
    @ManyToOne
    @JoinColumn(name = "order_key")
    private DimOrder order;
    
    @ManyToOne
    @JoinColumn(name = "customer_key")
    private DimCustomer customer;
    
    @ManyToOne
    @JoinColumn(name = "product_key")
    private DimProduct product;
    
    @ManyToOne
    @JoinColumn(name = "date_key")
    private DimDate dateKey;
    
    private Integer quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    private BigDecimal cost;
    private BigDecimal revenue;
    private BigDecimal profit;
    
    // Getters and setters
    // Constructors
}