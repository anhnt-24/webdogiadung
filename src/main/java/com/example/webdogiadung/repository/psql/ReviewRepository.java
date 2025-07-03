package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, String>, JpaSpecificationExecutor<ReviewEntity> {
    
    List<ReviewEntity> findByProduct_Id(String productId); //

    List<ReviewEntity> findByPhone(String phone);
}
