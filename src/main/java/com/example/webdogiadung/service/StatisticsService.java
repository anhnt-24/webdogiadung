package com.example.webdogiadung.service;

import com.example.webdogiadung.StatisticsDTO.OrderStatusStatsDto;
import com.example.webdogiadung.StatisticsDTO.RevenueDTO;
import com.example.webdogiadung.StatisticsDTO.TopCustomerDto;
import com.example.webdogiadung.StatisticsDTO.TopProductDto;
import com.example.webdogiadung.repository.sqlserver.DimCustomerRepository;
import com.example.webdogiadung.repository.sqlserver.DimOrderRepository;
import com.example.webdogiadung.repository.sqlserver.DimProductRepository;
import com.example.webdogiadung.repository.sqlserver.FactSalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {
    private final DimCustomerRepository dimCustomerRepository;
    private final FactSalesRepository factSalesRepository;
    private final DimProductRepository dimProductRepository;
    private final DimOrderRepository dimOrderRepository;
    public List<TopCustomerDto> getTop5Customers() {
        return dimCustomerRepository.findTop5Customers(PageRequest.of(0, 5));
    }

    public List<RevenueDTO> getRecent10DaysRevenue() {
        return factSalesRepository.findRecent10DaysRevenue();
    }

    public List<RevenueDTO> getRecent10WeeksRevenue() {
        return factSalesRepository.findRecent10WeeksRevenue();
    }

    public List<RevenueDTO> getRecent10MonthsRevenue() {
        return factSalesRepository.findRecent10MonthsRevenue();
    }

    public List<RevenueDTO> getRecent10YearsRevenue() {
        return factSalesRepository.findRecent10YearsRevenue();
    }

    public List<TopProductDto> getTop5BestSellingProducts() {
        return dimProductRepository.findTop5BestSellingProducts(PageRequest.of(0, 5));
    }
    public int countLowStockProducts() {
        return dimProductRepository.countByStockLessThanEqual(5); // ví dụ: tồn kho <= 5
    }
    public List<OrderStatusStatsDto> getOrderStatusStats() {
        return dimOrderRepository.getOrderStatusStatisticsNative().stream()
                .map(result -> new OrderStatusStatsDto(
                        (String) result[0],
                        ((Number) result[1]).longValue(),
                        ((Number) result[2]).doubleValue()
                ))
                .collect(Collectors.toList());
    }

}