package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.Constants.CategoryEntity;
import com.example.webdogiadung.service.interfa.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class CategoryController implements BaseControllerInterface<CategoryRequest, CategoryEntity, CategoryResponse, Long> {

    private final CategoryService categoryService;

    @Override
    public ApiResponse<CategoryResponse> create(CategoryRequest request) {
        var cateResponse = categoryService.create(request);
        return ApiResponse.<CategoryResponse>builder()
                .data(cateResponse)
                .status(Status.OK).build();
    }

    @Override
    public ApiResponse<CategoryResponse> update(CategoryRequest request) {
        return null;
    }

    @Override
    public ApiResponse<PagingResponse<CategoryResponse>> getAll(CategoryEntity request) {
        return null;
    }

    @Override
    public ApiResponse<CategoryResponse> getById(Long aLong) {
        return null;
    }

    @Override
    public ApiResponse<String> deleteById(Long aLong) {
        return null;
    }

    @Override
    public ApiResponse<String> deleteByListId(List<Long> listId) {
        return null;
    }
}
