package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.ChatMessageRequest;
import com.example.webdogiadung.dto.response.ChatMessageResponse;
import com.example.webdogiadung.entity.psql.ChatMessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper extends BaseEntityMapper<ChatMessageEntity, ChatMessageRequest, ChatMessageResponse> {
}
