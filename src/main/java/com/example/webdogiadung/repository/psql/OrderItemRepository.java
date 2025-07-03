package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String>, JpaSpecificationExecutor<OrderItemEntity> {
}
