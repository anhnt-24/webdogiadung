package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.ProductDescriptionEntity;
import com.example.webdogiadung.entity.psql.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDescriptionRepository extends JpaRepository<ProductDescriptionEntity, String> {
    Optional<ProductDescriptionEntity> findByProduct(ProductEntity product);
}
