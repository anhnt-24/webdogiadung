package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.ChatMessageRequest;
import com.example.webdogiadung.dto.request.search.ChatMessageSearchRequest;
import com.example.webdogiadung.dto.response.ChatMessageResponse;

public interface ChatMessageServiceInterface extends BaseServiceInterface<ChatMessageSearchRequest, ChatMessageRequest, ChatMessageResponse,Long> {
}
