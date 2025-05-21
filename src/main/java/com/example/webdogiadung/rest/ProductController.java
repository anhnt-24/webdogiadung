package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.ProductRequest;
import com.example.webdogiadung.dto.request.search.ProductSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ProductResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/")
public class ProductController {


    private final ProductService productService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> create(@RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .status(Status.OK)
                .data(productService.create(request))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> update(@RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .status(Status.UPDATED)
                .data(productService.update(request))
                .build();
    }

    @PostMapping("get/all")
    public ApiResponse<PagingResponse<ProductResponse>> getAll(@RequestBody ProductSearchRequest request) {
        return ApiResponse.<PagingResponse<ProductResponse>>builder()
                .status(Status.OK)
                .data(productService.getAll(request))
                .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<ProductResponse> getById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .status(Status.OK)
                .data(productService.getById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}/{isDeleted}")
    public ApiResponse<String> deleteById(@PathVariable("id") String id, @PathVariable("isDeleted") boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(productService.deleteById(id, isDeleted))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete")
    public ApiResponse<String> deleteByListId(@RequestBody List<String> listId, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(productService.deleteByListId(listId, isDeleted))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("restore")
    public ApiResponse<String> restore(@RequestBody List<String> listId){
        return ApiResponse.<String>builder()
                .status(Status.UPDATED)
                .data(productService.restore(listId))
                .build();
    }

}
