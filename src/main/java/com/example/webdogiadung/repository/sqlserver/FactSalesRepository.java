// FactSalesRepository.java
package com.example.webdogiadung.repository.sqlserver;

import com.example.webdogiadung.StatisticsDTO.RevenueDTO;
import com.example.webdogiadung.entity.sqlserver.FactSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FactSalesRepository extends JpaRepository<FactSales, Long> {
    
    @Query("SELECT new com.example.webdogiadung.StatisticsDTO.RevenueDTO(d.date, d.day, d.week, d.month, d.year, SUM(fs.revenue),SUM(fs.profit)) " +
           "FROM FactSales fs JOIN fs.dateKey d " +
           "GROUP BY d.date, d.day, d.week, d.month, d.year " +
           "ORDER BY d.date DESC LIMIT 10")
    List<RevenueDTO> findRecent10DaysRevenue();
    
    @Query("SELECT new com.example.webdogiadung.StatisticsDTO.RevenueDTO(null, null, d.week, null, d.year, SUM(fs.revenue),SUM(fs.profit)) " +
           "FROM FactSales fs JOIN fs.dateKey d " +
           "GROUP BY d.week, d.year " +
           "ORDER BY d.year DESC, d.week DESC LIMIT 10")
    List<RevenueDTO> findRecent10WeeksRevenue();
    
    @Query("SELECT new com.example.webdogiadung.StatisticsDTO.RevenueDTO(null, null, null, d.month, d.year, SUM(fs.revenue),SUM(fs.profit)) " +
           "FROM FactSales fs JOIN fs.dateKey d " +
           "GROUP BY d.month, d.year " +
           "ORDER BY d.year DESC, d.month DESC LIMIT 10")
    List<RevenueDTO> findRecent10MonthsRevenue();
    
    @Query("SELECT new com.example.webdogiadung.StatisticsDTO.RevenueDTO(null, null, null, null, d.year, SUM(fs.revenue),SUM(fs.profit)) " +
           "FROM FactSales fs JOIN fs.dateKey d " +
           "GROUP BY d.year " +
           "ORDER BY d.year DESC LIMIT 10")
    List<RevenueDTO> findRecent10YearsRevenue();
}