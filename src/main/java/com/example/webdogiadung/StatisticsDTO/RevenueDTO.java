// RevenueDTO.java
package com.example.webdogiadung.StatisticsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDTO {
    private LocalDate date;
    private Integer day;
    private Integer week;
    private Integer month;
    private Integer year;
    private BigDecimal revenue;
    private BigDecimal profit;
}