package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity,String>, JpaSpecificationExecutor<GuestEntity> {
}
