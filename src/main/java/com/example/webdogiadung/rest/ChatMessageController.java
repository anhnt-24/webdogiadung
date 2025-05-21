package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.ChatMessageRequest;
import com.example.webdogiadung.dto.request.search.ChatMessageSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ChatMessageResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat-message/")
public class ChatMessageController{

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @PostMapping("create")
    public ApiResponse<ChatMessageResponse> create(@RequestBody ChatMessageRequest request) {
        return null;
    }

    @PostMapping("update")
    public ApiResponse<ChatMessageResponse> update(@RequestBody ChatMessageRequest request) {
        return null;
    }

    @PostMapping("get/all")
    public ApiResponse<PagingResponse<ChatMessageResponse>> getAll(@RequestBody ChatMessageSearchRequest request) {
        return ApiResponse.<PagingResponse<ChatMessageResponse>>builder()
                .status(Status.OK)
                .data(chatMessageService.getAll(request))
                .build();
    }

    @MessageMapping("/chat")
    public ChatMessageResponse sendMessage(@Payload ChatMessageRequest message) {
        ChatMessageResponse chatMessageResponse=chatMessageService.create(message);
        messagingTemplate.convertAndSendToUser(message.getReceiverId(),"/chat",chatMessageResponse);
        return chatMessageResponse;
    }

}
