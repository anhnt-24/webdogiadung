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
@Table(name = "dim_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimProduct {
    @Id
    @Column(name = "product_key")
    private Integer productKey;
    @Column(name = "product_id")
    private String productId;
    private String name;
    private String brand;
    private String category;
    @Column(name = "import_price")
    private BigDecimal importPrice;
    @Column(name = "selling_price")
    private BigDecimal sellingPrice;
    @Column(name = "promotion_price")
    private BigDecimal promotionPrice;
    private Integer sold;
    private Integer stock;
    private BigDecimal  rating;
    
}