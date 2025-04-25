package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.OrderItemRequest;
import com.example.webdogiadung.dto.request.search.OrderItemSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.OrderItemResponse;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-item/")
@RequiredArgsConstructor
public class OrderItemController implements BaseControllerInterface<OrderItemSearchRequest, OrderItemRequest, OrderItemResponse, String>{

    private final OrderItemService orderItemService;


    @Override
    @PostMapping(value = "create")
    public ApiResponse<OrderItemResponse> create(OrderItemRequest request) {
        return ApiResponse.<OrderItemResponse>builder()
                .status(Status.OK)
                .data(orderItemService.create(request))
                .build();
    }

    @Override
    @PutMapping(value = "update")
    public ApiResponse<OrderItemResponse> update(OrderItemRequest request) {
        return ApiResponse.<OrderItemResponse>builder()
                .status(Status.UPDATED)
                .data(orderItemService.update(request))
                .build();
    }

    @Override
    public ApiResponse<PagingResponse<OrderItemResponse>> getAll(OrderItemSearchRequest request) {
        return ApiResponse.<PagingResponse<OrderItemResponse>>builder()
                .status(Status.OK)
                .data(orderItemService.getAll(request))
                .build();
    }

    @Override
    public ApiResponse<OrderItemResponse> getById(String id) {
        return ApiResponse.<OrderItemResponse>builder()
                .status(Status.OK)
                .data(orderItemService.getById(id))
                .build();
    }

    @Override
    public ApiResponse<String> deleteById(String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(orderItemService.deleteById(id,isDeleted))
                .build();
    }

    @Override
    public ApiResponse<String> deleteByListId(List<String> listId, boolean isDeleted) {
        return null;
    }
}
