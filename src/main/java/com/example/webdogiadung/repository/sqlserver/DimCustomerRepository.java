package com.example.webdogiadung.repository.sqlserver;

import com.example.webdogiadung.StatisticsDTO.TopCustomerDto;
import com.example.webdogiadung.entity.sqlserver.DimCustomer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DimCustomerRepository extends JpaRepository<DimCustomer, Long> {
    

    @Query("""
    SELECT new com.example.webdogiadung.StatisticsDTO.TopCustomerDto(
        d.customerKey,
        d.clientId,
        d.name,
        d.email,
        d.phone,
        d.address,
        d.totalSpent,
        d.numOrder
    )
    FROM DimCustomer d
    ORDER BY d.totalSpent DESC
""")
    List<TopCustomerDto> findTop5Customers(org.springframework.data.domain.Pageable pageable);

}
