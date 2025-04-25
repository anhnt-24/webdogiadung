package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.search.ClientSearchRequest;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.interfa.ClientServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientServiceInterface {
    @Override
    public ClientResponse create(ClientRequest data) {
        return null;
    }

    @Override
    public PagingResponse<ClientResponse> getAll(ClientSearchRequest request) {
        return null;
    }

    @Override
    public ClientResponse getById(String s) {
        return null;
    }

    @Override
    public String deleteById(String s, boolean isDelete) {
        return "";
    }

    @Override
    public ClientResponse update(ClientRequest data) {
        return null;
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        return "";
    }
}
