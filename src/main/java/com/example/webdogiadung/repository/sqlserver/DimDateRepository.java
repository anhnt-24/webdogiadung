package com.example.webdogiadung.repository.sqlserver;

import com.example.webdogiadung.entity.sqlserver.DimDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface DimDateRepository extends JpaRepository<DimDate, Long> {
}