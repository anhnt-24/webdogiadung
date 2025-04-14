package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.search.CategorySearchRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;

public interface CategoryInterfa  extends BaseServiceInterface<CategorySearchRequest, CategoryRequest, CategoryResponse,String>{
}
