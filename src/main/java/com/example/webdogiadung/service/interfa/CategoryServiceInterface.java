package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.search.CategorySearchRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.SearchResponse;

import java.util.List;

public interface CategoryServiceInterface extends BaseServiceInterface<CategorySearchRequest, CategoryRequest, CategoryResponse,String>{
    String restore(List<String> listId);
    List<SearchResponse> getAllForSelect();
}
