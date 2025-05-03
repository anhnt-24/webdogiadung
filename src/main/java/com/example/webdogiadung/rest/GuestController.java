package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.GuestRequest;
import com.example.webdogiadung.dto.request.search.GuestSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.GuestResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/guest/")
@RequiredArgsConstructor
public class GuestController implements BaseControllerInterface<GuestSearchRequest, GuestRequest, GuestResponse, String> {
    private final GuestService guessService;
    @Override
    @PostMapping("create")
    public ApiResponse<GuestResponse> create(@RequestBody GuestRequest request) {
        return ApiResponse.<GuestResponse>builder()
                .status(Status.CREATED)
                .data(guessService.create(request))
                .build();
    }

    @Override
    @PostMapping("update")
    public ApiResponse<GuestResponse> update(@RequestBody GuestRequest request) {
        return null;
    }

    @Override
    public ApiResponse<PagingResponse<GuestResponse>> getAll(GuestSearchRequest request) {
        return ApiResponse.<PagingResponse<GuestResponse>>builder()
                .status(Status.OK)
                .data(guessService.getAll(request))
                .build();
    }

    @Override
    public ApiResponse<String> deleteById(String s, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data("Xóa thành công. ")
                    .build();
    }

    @Override
    public ApiResponse<GuestResponse> getById(String id) {
        return ApiResponse.<GuestResponse>builder()
                .status(Status.OK)
                .data(guessService.getById(id))
                .build();
    }
}
