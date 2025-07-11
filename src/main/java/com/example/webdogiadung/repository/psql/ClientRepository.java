package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, String>, JpaSpecificationExecutor<ClientEntity> {
    Optional<ClientEntity> findByNameAndEmailAndPhone(String name, String email, String phone);
}
