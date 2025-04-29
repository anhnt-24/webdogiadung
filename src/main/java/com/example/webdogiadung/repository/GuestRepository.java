package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity,String>, JpaSpecificationExecutor<GuestEntity> {
}
