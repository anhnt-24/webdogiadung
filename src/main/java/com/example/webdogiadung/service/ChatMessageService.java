package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ChatMessageRequest;
import com.example.webdogiadung.dto.request.search.ChatMessageSearchRequest;
import com.example.webdogiadung.dto.response.ChatMessageResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.ChatMessageEntity;
import com.example.webdogiadung.mapper.ChatMessageMapper;
import com.example.webdogiadung.repository.ChatMessageRepository;
import com.example.webdogiadung.service.interfa.ChatMessageServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements ChatMessageServiceInterface {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public ChatMessageResponse create(ChatMessageRequest request) {
        ChatMessageEntity chatMessageEntity=chatMessageMapper.toEntity(request);
        return chatMessageMapper.toResponse(chatMessageRepository.save(chatMessageEntity)) ;
    }

    @Override
    public PagingResponse<ChatMessageResponse> getAll(ChatMessageSearchRequest request) {
        Page<ChatMessageEntity> chatMessageEntities = chatMessageRepository.findAll(request.specification(),request.getPaging().pageable());
        return PagingResponse.<ChatMessageResponse>builder()
                .contents(chatMessageMapper.toResponse(chatMessageEntities.getContent()))
                .paging(new PageableData()
                        .setPageNumber(chatMessageEntities.getNumber())
                        .setPageSize(chatMessageEntities.getSize())
                        .setTotalPage(chatMessageEntities.getTotalPages())
                        .setTotalRecord(chatMessageEntities.getTotalElements()))
                .build();
    }

    @Override
    public ChatMessageResponse getById(Long aLong) {
        return null;
    }

    @Override
    public String deleteById(Long aLong, boolean isDelete) {
        return "";
    }

    @Override
    public ChatMessageResponse update(ChatMessageRequest data) {
        return null;
    }

    @Override
    public String deleteByListId(List<Long> listId, boolean isDelete) {
        return "";
    }
}
