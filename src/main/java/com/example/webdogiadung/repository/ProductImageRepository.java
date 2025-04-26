package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.ProductEntity;
import com.example.webdogiadung.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
    List<ProductImage> findByProductEntityId(String productId);
}
