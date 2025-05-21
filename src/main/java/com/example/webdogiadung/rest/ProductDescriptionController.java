package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.ProductDescriptionRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ProductDescriptionResponse;
import com.example.webdogiadung.service.interfa.ProductDescriptionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-descriptions/")
@RequiredArgsConstructor
public class ProductDescriptionController {

    private final ProductDescriptionServiceInterface productDescriptionService;

    @GetMapping("get/{id}")
    public ApiResponse<ProductDescriptionResponse>  getProductDescription(@PathVariable String id) {
        return ApiResponse.<ProductDescriptionResponse>builder()
                .status(Status.OK)
                .data(productDescriptionService.getByProductId(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ApiResponse<ProductDescriptionResponse>  updateProductDescription(@RequestBody ProductDescriptionRequest request) {
        return ApiResponse.<ProductDescriptionResponse>builder()
                .status(Status.OK)
                .data(productDescriptionService.update(request))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteProductDescription(@PathVariable String id) {
        productDescriptionService.delete(id);
        return ApiResponse.<Void>builder()
                .status(Status.DELETED)
                .build();
    }
}
