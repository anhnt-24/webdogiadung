package com.example.webdogiadung.repository;

import com.example.webdogiadung.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity,Long>, JpaSpecificationExecutor<ChatMessageEntity> {
}
