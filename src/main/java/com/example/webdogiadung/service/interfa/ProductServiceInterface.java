package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.ProductRequest;
import com.example.webdogiadung.dto.request.search.ProductSearchRequest;
import com.example.webdogiadung.dto.response.ProductResponse;

public interface ProductServiceInterface extends BaseServiceInterface<ProductSearchRequest, ProductRequest, ProductResponse,String>{
}
