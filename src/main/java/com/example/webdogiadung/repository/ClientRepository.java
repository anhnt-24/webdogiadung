package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, String>, JpaSpecificationExecutor<ClientEntity> {
    Optional<ClientEntity> findByEmail(String email);
    Optional<ClientEntity> findByPhone(String phone);
}
