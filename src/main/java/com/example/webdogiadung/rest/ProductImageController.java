package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.ProductImageRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ProductImageResponse;
import com.example.webdogiadung.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product_image")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping("/upload")
    public ApiResponse<Void> uploadProductImage(ProductImageRequest productImageRequest) {
        productImageService.saveProductImage(productImageRequest);
        return ApiResponse.<Void>builder()
                .status(Status.CREATED)
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<List<ProductImageResponse>> getProductImages(@PathVariable String productId) {
        List<ProductImageResponse> images = productImageService.getAllProductImagesByProductId(productId);
        return ApiResponse.<List<ProductImageResponse>>builder()
                .status(Status.OK)
                .data(images)
                .build();
    }

    @DeleteMapping("/{imageId}")
    public ApiResponse<Void> deleteProductImage(@PathVariable String imageId) {
        productImageService.deleteProductImage(imageId);
        return ApiResponse.<Void>builder()
                .status(Status.OK)
                .build();
    }
}
