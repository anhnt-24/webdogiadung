package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.OrderResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface OrderServiceInterface extends BaseServiceInterface<OrderSearchRequest, OrderRequest, OrderResponse, String>{
}
