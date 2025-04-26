package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.ProductImageRequest;
import com.example.webdogiadung.dto.response.ProductImageResponse;

import java.util.List;

public interface ProductImageInterface {

    void saveProductImage(ProductImageRequest request);

    List<ProductImageResponse> getAllProductImagesByProductId(String productId);

    void deleteProductImage(String imageId);
}
