package com.example.webdogiadung.rest;

import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.search.ClientSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client/")
@RequiredArgsConstructor
public class ClientController implements BaseControllerInterface<ClientSearchRequest, ClientRequest, ClientResponse, String>{

    @Override
    public ApiResponse<ClientResponse> create(ClientRequest request) {
        return null;
    }

    @Override
    public ApiResponse<ClientResponse> update(ClientRequest request) {
        return null;
    }

    @Override
    public ApiResponse<PagingResponse<ClientResponse>> getAll(ClientSearchRequest request) {
        return null;
    }

    @Override
    public ApiResponse<ClientResponse> getById(String s) {
        return BaseControllerInterface.super.getById(s);
    }

    @Override
    public ApiResponse<String> deleteById(String s, boolean isDeleted) {
        return BaseControllerInterface.super.deleteById(s, isDeleted);
    }

    @Override
    public ApiResponse<String> deleteByListId(List<String> listId, boolean isDeleted) {
        return null;
    }
}
