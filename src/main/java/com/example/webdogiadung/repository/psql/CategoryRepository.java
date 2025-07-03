package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.dto.response.SearchResponse;
import com.example.webdogiadung.entity.psql.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,String>, JpaSpecificationExecutor<CategoryEntity> {
    Optional<CategoryEntity> findByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
    @Query("SELECT new com.example.webdogiadung.dto.response.SearchResponse(c.id, c.name) FROM CategoryEntity c")
    List<SearchResponse> findAllForSelect();

}
