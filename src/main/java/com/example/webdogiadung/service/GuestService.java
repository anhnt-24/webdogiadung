package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.GuestRequest;
import com.example.webdogiadung.dto.request.search.GuestSearchRequest;
import com.example.webdogiadung.dto.response.GuestResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.GuestEntity;
import com.example.webdogiadung.mapper.GuestMapper;
import com.example.webdogiadung.repository.GuestRepository;
import com.example.webdogiadung.service.interfa.GuessServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService implements GuessServiceInterface {
    private final GuestRepository guessRepository;
    private final GuestMapper guestMapper;

    @Override
    public GuestResponse create(GuestRequest data) {
        GuestEntity guestEntity = guestMapper.toEntity(data);
        return guestMapper.toResponse(
                guessRepository.save(guestEntity)
        );
    }

    @Override
    public PagingResponse<GuestResponse> getAll(GuestSearchRequest request) {
        Page<GuestEntity> guestEntities = guessRepository.findAll(request.specification(),request.getPaging().pageable());
        return PagingResponse.<GuestResponse>builder()
                .contents(guestMapper.toResponse(guestEntities.getContent()))
                .paging(new PageableData()
                        .setTotalRecord(guestEntities.getTotalElements())
                        .setTotalPage(guestEntities.getTotalPages())
                        .setPageSize(guestEntities.getSize())
                        .setPageNumber(guestEntities.getNumber()))

                .build();
    }

    @Override
    public GuestResponse getById(String id) {
        GuestEntity guestEntity= guessRepository.findById(id).orElse(null);
        return guestMapper.toResponse(guestEntity);
    }

    @Override
    public String deleteById(String id, boolean isDelete) {
        guessRepository.deleteById(id);
        return "";
    }

    @Override
    public GuestResponse update(GuestRequest data) {
        return null;
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        return "";
    }
}
