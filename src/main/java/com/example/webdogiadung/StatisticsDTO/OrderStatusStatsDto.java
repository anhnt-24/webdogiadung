package com.example.webdogiadung.StatisticsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusStatsDto {
    private String status;
    private Long total;
    private Double percentage;
}