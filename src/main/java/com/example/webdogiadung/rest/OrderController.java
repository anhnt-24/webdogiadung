package com.example.webdogiadung.rest;

import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/")
@RequiredArgsConstructor
public class OrderController implements BaseControllerInterface<OrderSearchRequest, OrderRequest, OrderResponse, String>{

    @Override
    public ApiResponse<OrderResponse> create(OrderRequest request) {
        return null;
    }

    @Override
    public ApiResponse<OrderResponse> update(OrderRequest request) {
        return null;
    }

    @Override
    public ApiResponse<PagingResponse<OrderResponse>> getAll(OrderSearchRequest request) {
        return null;
    }

    @Override
    public ApiResponse<OrderResponse> getById(String s) {
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
