package com.example.webdogiadung.repository.psql;

import com.example.webdogiadung.entity.psql.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {
    Optional<AccountEntity> findByEmail(String email);
}
