package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> , JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findByName(String name);
    Boolean existsByNameAndIdNot(String name, String id);
}
