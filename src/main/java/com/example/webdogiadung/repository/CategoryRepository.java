package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.Constants.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
