package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,String>, JpaSpecificationExecutor<CategoryEntity> {
    Optional<CategoryEntity> findByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
}
