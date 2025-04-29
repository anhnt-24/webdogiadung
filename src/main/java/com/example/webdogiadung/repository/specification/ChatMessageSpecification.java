package com.example.webdogiadung.repository.specification;

import com.example.webdogiadung.entity.ChatMessageEntity;
import org.springframework.util.ObjectUtils;

public class ChatMessageSpecification extends BaseSpecification<ChatMessageEntity> {
    private final String RECEIVER_ID = "receiverId";
    private final String SENDER_ID = "senderId";
    private final String IS_READ = "isRead";

    public static ChatMessageSpecification builder() {
        return new ChatMessageSpecification();
    }

    public ChatMessageSpecification withReceiverAndSender(String receiverId, String senderId) {
            this.specifications.add((root, query, cb) ->
                    cb.or(
                    cb.and(
                            cb.equal(root.get(RECEIVER_ID), senderId),
                            cb.equal(root.get(SENDER_ID), receiverId)
                    ),
                            cb.and(
                                    cb.equal(root.get(RECEIVER_ID), receiverId),
                                    cb.equal(root.get(SENDER_ID), senderId)
                            ))
                    )

            ;
        return this;
    }
}
