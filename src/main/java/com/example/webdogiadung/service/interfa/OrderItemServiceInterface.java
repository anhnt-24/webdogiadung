package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.OrderItemRequest;
import com.example.webdogiadung.dto.request.search.OrderItemSearchRequest;
import com.example.webdogiadung.dto.response.OrderItemResponse;

public interface OrderItemServiceInterface extends BaseServiceInterface<OrderItemSearchRequest, OrderItemRequest, OrderItemResponse, String>{
}
