package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.ProductDescriptionEntity;
import com.example.webdogiadung.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDescriptionRepository extends JpaRepository<ProductDescriptionEntity, String> {
    Optional<ProductDescriptionEntity> findByProduct(ProductEntity product);
}
