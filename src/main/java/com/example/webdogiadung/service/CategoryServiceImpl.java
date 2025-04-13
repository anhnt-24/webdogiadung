package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.Constants.CategoryEntity;
import com.example.webdogiadung.repository.CategoryRepository;
import com.example.webdogiadung.service.interfa.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse create(CategoryRequest data) {
        var newCategory = new CategoryEntity();
        modelMapper.typeMap(CategoryRequest.class, CategoryEntity.class)
                .addMappings(mapper -> mapper.skip(CategoryEntity::setId));
        modelMapper.map(data,newCategory);
        return CategoryResponse.fromCategory(categoryRepository.save(newCategory));
    }

    @Override
    public PagingResponse<CategoryResponse> getAll(CategoryEntity request) {
        return null;
    }

    @Override
    public CategoryResponse getById(Long aLong) {
        return null;
    }

    @Override
    public String deleteById(Long aLong) {
        return "";
    }

    @Override
    public CategoryResponse update(CategoryRequest data) {
        return null;
    }

    @Override
    public String deleteByListId(List<Long> listId) {
        return "";
    }
}
