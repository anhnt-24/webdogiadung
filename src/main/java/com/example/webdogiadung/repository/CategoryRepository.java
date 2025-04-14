package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,String>, JpaSpecificationExecutor<CategoryEntity> {
}
