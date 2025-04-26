package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.search.CategorySearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.SearchResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category/")
public class CategoryController implements BaseControllerInterface<CategorySearchRequest, CategoryRequest, CategoryResponse,String>{

    private final CategoryService categoryService;

    @Override
    @PostMapping(value = "create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<CategoryResponse> create(CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .status(Status.OK)
                .data(categoryService.create(request))
                .build();
    }

    @Override
    @PutMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<CategoryResponse> update(CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .status(Status.UPDATED)
                .data(categoryService.update(request))
                .build();
    }

    @Override
    public ApiResponse<PagingResponse<CategoryResponse>> getAll(CategorySearchRequest request) {
        return ApiResponse.<PagingResponse<CategoryResponse>>builder()
                .status(Status.OK)
                .data(categoryService.getAll(request))
                .build();
    }

    @Override
    public ApiResponse<CategoryResponse> getById(String id) {
        return ApiResponse.<CategoryResponse>builder()
                .status(Status.OK)
                .data(categoryService.getById(id))
                .build();
    }

    @Override
    public ApiResponse<String> deleteById(String id,boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(categoryService.deleteById(id,isDeleted))
                .build();
    }

    @Override
    public ApiResponse<String> deleteByListId(List<String> listId, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(categoryService.deleteByListId(listId,isDeleted))
                .build();
    }
    @PutMapping("restore")
    public ApiResponse<String> restore(@RequestBody List<String> listId){
        return ApiResponse.<String>builder()
                .status(Status.UPDATED)
                .data(categoryService.restore(listId))
                .build();
    }

    @GetMapping("select")
    public ApiResponse<List<SearchResponse>> select(){
        return ApiResponse.<List<SearchResponse>>builder()
                .status(Status.OK)
                .data(categoryService.getAllForSelect())
                .build();
    }
}
