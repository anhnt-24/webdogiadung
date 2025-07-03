package com.example.webdogiadung.repository.sqlserver;

import com.example.webdogiadung.StatisticsDTO.TopProductDto;
import com.example.webdogiadung.entity.sqlserver.DimProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DimProductRepository extends JpaRepository<DimProduct, Integer> {
    int countByStockLessThanEqual(int stockThreshold);
    @Query("""
        SELECT new com.example.webdogiadung.StatisticsDTO.TopProductDto(
            p.productKey,
            p.productId,
            p.name,
            p.brand,
            p.category,
            p.sellingPrice,
            p.sold,
            p.rating
        )
        FROM DimProduct p
        ORDER BY p.sold DESC
    """)
    List<TopProductDto> findTop5BestSellingProducts(Pageable pageable);
}
