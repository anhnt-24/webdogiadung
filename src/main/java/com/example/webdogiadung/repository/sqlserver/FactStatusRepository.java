package com.example.webdogiadung.repository.sqlserver;

import com.example.webdogiadung.entity.sqlserver.FactStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactStatusRepository extends JpaRepository<FactStatus, Long> {
    FactStatus findByStatus(String status);
    List<FactStatus> findByPercentageGreaterThan(double minPercentage);
}