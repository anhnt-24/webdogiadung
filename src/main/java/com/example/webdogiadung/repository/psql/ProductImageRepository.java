package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
    List<ProductImage> findByProductEntityId(String productId);
}
