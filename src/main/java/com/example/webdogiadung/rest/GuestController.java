package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.GuestRequest;
import com.example.webdogiadung.dto.request.search.GuestSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.GuestResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guest/")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guessService;

    @PostMapping("create")
    public ApiResponse<GuestResponse> create(@RequestBody GuestRequest request) {
        return ApiResponse.<GuestResponse>builder()
                .status(Status.CREATED)
                .data(guessService.create(request))
                .build();
    }

    @PostMapping("update")
    public ApiResponse<GuestResponse> update(@RequestBody GuestRequest request) {
        return null;
    }

    @PostMapping("get/all")
    public ApiResponse<PagingResponse<GuestResponse>> getAll(@RequestBody GuestSearchRequest request) {
        return ApiResponse.<PagingResponse<GuestResponse>>builder()
                .status(Status.OK)
                .data(guessService.getAll(request))
                .build();
    }

    @DeleteMapping("delete/{id}")
    public ApiResponse<String> deleteById(@PathVariable String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data("Xóa thành công. ")
                    .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<GuestResponse> getById(@PathVariable String id) {
        return ApiResponse.<GuestResponse>builder()
                .status(Status.OK)
                .data(guessService.getById(id))
                .build();
    }
}
