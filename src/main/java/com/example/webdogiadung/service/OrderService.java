package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.interfa.OrderServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    @Override
    public OrderResponse create(OrderRequest data) {
        return null;
    }

    @Override
    public PagingResponse<OrderResponse> getAll(OrderSearchRequest request) {
        return null;
    }

    @Override
    public OrderResponse getById(String s) {
        return null;
    }

    @Override
    public String deleteById(String s, boolean isDelete) {
        return "";
    }

    @Override
    public OrderResponse update(OrderRequest data) {
        return null;
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        return "";
    }
}
