package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.ProductRequest;
import com.example.webdogiadung.dto.request.search.ProductSearchRequest;
import com.example.webdogiadung.dto.response.ProductResponse;

import java.util.List;

public interface ProductServiceInterface extends BaseServiceInterface<ProductSearchRequest, ProductRequest, ProductResponse,String>{
    public String restore(List<String> listId);
}
