package com.example.webdogiadung.dto.request.search;

import com.example.webdogiadung.entity.ChatMessageEntity;
import com.example.webdogiadung.repository.specification.ChatMessageSpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessageSearchRequest extends FilteringRequest<ChatMessageEntity> {
    String senderId;
    String receiverId;
    @Override
    public Specification<ChatMessageEntity> specification() {
        return ChatMessageSpecification.builder()
                .withReceiverAndSender(senderId, receiverId)
                .build();
    }
}
