package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.search.ClientSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client/")
@RequiredArgsConstructor
public class ClientController implements BaseControllerInterface<ClientSearchRequest, ClientRequest, ClientResponse, String>{

    private final ClientService clientService;

    @Override
    @PostMapping(value = "create")
    public ApiResponse<ClientResponse> create(ClientRequest request) {
        return ApiResponse.<ClientResponse>builder()
                .status(Status.OK)
                .data(clientService.create(request))
                .build();
    }

    @Override
    @PutMapping(value = "update")
    public ApiResponse<ClientResponse> update(ClientRequest request) {
        return ApiResponse.<ClientResponse>builder()
                .status(Status.UPDATED)
                .data(clientService.update(request))
                .build();
    }

    @Override
    public ApiResponse<PagingResponse<ClientResponse>> getAll(ClientSearchRequest request) {
        return ApiResponse.<PagingResponse<ClientResponse>>builder()
                .status(Status.OK)
                .data(clientService.getAll(request))
                .build();
    }

    @Override
    public ApiResponse<ClientResponse> getById(String id) {
        return ApiResponse.<ClientResponse>builder()
                .status(Status.OK)
                .data(clientService.getById(id))
                .build();
    }

    @Override
    public ApiResponse<String> deleteById(String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(clientService.deleteById(id,isDeleted))
                .build();
    }

    @Override
    public ApiResponse<String> deleteByListId(List<String> listId, boolean isDeleted) {
        return null;
    }
}
