package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.OrderResponse;

public interface OrderServiceInterface extends BaseServiceInterface<OrderSearchRequest, OrderRequest, OrderResponse, String>{
}
