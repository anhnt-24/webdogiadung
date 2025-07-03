package com.example.webdogiadung.entity.sqlserver;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dim_date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimDate {
    @Id
    @Column(name = "date_key")
    private Integer dateKey;
    
    private LocalDate date;
    private Integer day;
    private Integer week;
    private Integer month;
    private Integer quarter;
    private Integer year;
    
    // Getters and setters
    // Constructors
}