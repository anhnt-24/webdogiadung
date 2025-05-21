package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.ProductDescriptionRequest;
import com.example.webdogiadung.dto.response.ProductDescriptionResponse;

public interface ProductDescriptionServiceInterface {

    ProductDescriptionResponse get(String id);

    ProductDescriptionResponse update(ProductDescriptionRequest request);

    ProductDescriptionResponse getByProductId(String productId);

    void delete(String id);
}
