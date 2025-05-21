package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.OrderItemRequest;
import com.example.webdogiadung.dto.request.search.OrderItemSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.OrderItemResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-item/")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping(value = "create")
    public ApiResponse<OrderItemResponse> create(@RequestBody OrderItemRequest request) {
        return ApiResponse.<OrderItemResponse>builder()
                .status(Status.OK)
                .data(orderItemService.create(request))
                .build();
    }


    @PutMapping(value = "update")
    public ApiResponse<OrderItemResponse> update(@RequestBody OrderItemRequest request) {
        return ApiResponse.<OrderItemResponse>builder()
                .status(Status.UPDATED)
                .data(orderItemService.update(request))
                .build();
    }
    @PostMapping("get/all")
    public ApiResponse<PagingResponse<OrderItemResponse>> getAll(@RequestBody OrderItemSearchRequest request) {
        return ApiResponse.<PagingResponse<OrderItemResponse>>builder()
                .status(Status.OK)
                .data(orderItemService.getAll(request))
                .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<OrderItemResponse> getById(@PathVariable String id) {
        return ApiResponse.<OrderItemResponse>builder()
                .status(Status.OK)
                .data(orderItemService.getById(id))
                .build();
    }

}
