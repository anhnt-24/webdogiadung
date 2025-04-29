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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/")
public class ProductController implements BaseControllerInterface<ProductSearchRequest, ProductRequest, ProductResponse, String> {


    private final ProductService productService;


    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> create(ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .status(Status.OK)
                .data(productService.create(request))
                .build();
    }

    @PutMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> update(ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .status(Status.UPDATED)
                .data(productService.update(request))
                .build();
    }

    public ApiResponse<PagingResponse<ProductResponse>> getAll(ProductSearchRequest request) {
        return ApiResponse.<PagingResponse<ProductResponse>>builder()
                .status(Status.OK)
                .data(productService.getAll(request))
                .build();
    }

    public ApiResponse<ProductResponse> getById(String id) {
        return ApiResponse.<ProductResponse>builder()
                .status(Status.OK)
                .data(productService.getById(id))
                .build();
    }

    public ApiResponse<String> deleteById(String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(productService.deleteById(id, isDeleted))
                .build();
    }

    public ApiResponse<String> deleteByListId(List<String> listId, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(productService.deleteByListId(listId, isDeleted))
                .build();
    }

    @PutMapping("restore")
    public ApiResponse<String> restore(@RequestBody List<String> listId){
        return ApiResponse.<String>builder()
                .status(Status.UPDATED)
                .data(productService.restore(listId))
                .build();
    }

}
