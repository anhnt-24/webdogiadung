package com.example.webdogiadung.rest;

import com.example.webdogiadung.StatisticsDTO.OrderStatusStatsDto;
import com.example.webdogiadung.StatisticsDTO.RevenueDTO;
import com.example.webdogiadung.StatisticsDTO.TopCustomerDto;
import com.example.webdogiadung.StatisticsDTO.TopProductDto;
import com.example.webdogiadung.service.ProductService;
import com.example.webdogiadung.service.StatisticsExportService;
import com.example.webdogiadung.service.StatisticsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final StatisticsExportService statisticsExportService;

    @GetMapping("/recent-days")
    public List<RevenueDTO> getRecent10DaysRevenue() {
        return statisticsService.getRecent10DaysRevenue().reversed();
    }

    @GetMapping("/recent-weeks")
    public List<RevenueDTO> getRecent10WeeksRevenue() {
        return statisticsService.getRecent10WeeksRevenue().reversed();
    }

    @GetMapping("/recent-months")
    public List<RevenueDTO> getRecent10MonthsRevenue() {
        return statisticsService.getRecent10MonthsRevenue().reversed();
    }

    @GetMapping("/recent-years")
    public List<RevenueDTO> getRecent10YearsRevenue() {
        return statisticsService.getRecent10YearsRevenue().reversed();
    }

    @GetMapping("/top5-customers")
    public List<TopCustomerDto> getTop5Customers() {
        return statisticsService.getTop5Customers();
    }
    @GetMapping("/top5-sold")
    public List<TopProductDto> getTop5BestSellingProducts() {
        return statisticsService.getTop5BestSellingProducts();
    }
    @GetMapping("/low-stock-count")
    public int getLowStockCount() {
        return statisticsService.countLowStockProducts();
    }

    @GetMapping("/export")
    public void exportAllStatistics(HttpServletResponse response) throws IOException {
        statisticsExportService.exportAllStatistics(response);
    }
    @GetMapping("/status")
    public List<OrderStatusStatsDto> getOrderStatusStatistics() {
        return statisticsService.getOrderStatusStats();
    }
}
