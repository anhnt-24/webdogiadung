package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>, JpaSpecificationExecutor<OrderEntity> {
}
