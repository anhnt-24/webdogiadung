package com.example.webdogiadung.repository.sqlserver;

import com.example.webdogiadung.StatisticsDTO.OrderStatusStatsDto;
import com.example.webdogiadung.entity.sqlserver.DimOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DimOrderRepository extends JpaRepository<DimOrder, Long> {
    @Query(value = """
    SELECT
        status,
        COUNT(*) AS total,
        ROUND(100.0 * COUNT(*) / (SELECT COUNT(*) FROM dim_order WHERE status IN ('DELIVERED', 'CANCELLED')), 2) AS percentage
    FROM
        dim_order
    WHERE
        status IN ('DELIVERED', 'CANCELLED')
    GROUP BY
        status
""", nativeQuery = true)
    List<Object[]> getOrderStatusStatisticsNative();
}