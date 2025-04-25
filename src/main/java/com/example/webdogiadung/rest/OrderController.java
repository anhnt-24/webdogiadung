package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/")
@RequiredArgsConstructor
public class OrderController implements BaseControllerInterface<OrderSearchRequest, OrderRequest, OrderResponse, String>{

    private final OrderService orderService;

    @Override
    @PostMapping(value = "create")
    public ApiResponse<OrderResponse> create(OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .status(Status.OK)
                .data(orderService.create(request))
                .build();
    }

    @Override
    @PutMapping(value = "update")
    public ApiResponse<OrderResponse> update(OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .status(Status.UPDATED)
                .data(orderService.update(request))
                .build();
    }

    @Override
    public ApiResponse<PagingResponse<OrderResponse>> getAll(OrderSearchRequest request) {
        return ApiResponse.<PagingResponse<OrderResponse>>builder()
                .status(Status.OK)
                .data(orderService.getAll(request))
                .build();
    }

    @Override
    public ApiResponse<OrderResponse> getById(String id) {
        return ApiResponse.<OrderResponse>builder()
                .status(Status.OK)
                .data(orderService.getById(id))
                .build();
    }

    @Override
    public ApiResponse<String> deleteById(String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(orderService.deleteById(id,isDeleted))
                .build();
    }

    @Override
    public ApiResponse<String> deleteByListId(List<String> listId, boolean isDeleted) {
        return null;
    }
}
