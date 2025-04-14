package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.search.CategorySearchRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.CategoryEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.mapper.CategoryMapper;
import com.example.webdogiadung.repository.CategoryRepository;
import com.example.webdogiadung.service.interfa.CategoryInterfa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryInterfa {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public CategoryResponse create(CategoryRequest data) {
        CategoryEntity categoryEntity=categoryMapper.toEntity(data);
        String imgUrl= (String) cloudinaryService.upload(data.getThumbnail()).get("secure_url");
        categoryEntity.setThumbnail(imgUrl);
        return categoryMapper.toResponse(categoryRepository.save(categoryEntity));
    }

    @Override
    public PagingResponse<CategoryResponse> getAll(CategorySearchRequest request) {
        Page<CategoryEntity> categoryEntityPage=categoryRepository.findAll(request.specification(),request.getPaging().pageable());
        return PagingResponse.<CategoryResponse>builder()
                .contents(categoryMapper.toResponse(categoryEntityPage.getContent()))
                .paging(new PageableData()
                        .setPageNumber(categoryEntityPage.getNumber())
                        .setPageSize(categoryEntityPage.getSize())
                        .setTotalPage(categoryEntityPage.getTotalPages())
                        .setTotalRecord(categoryEntityPage.getTotalElements()))
                .build();
    }

    @Override
    public CategoryResponse getById(String id) {
        return categoryMapper.toResponse(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public String deleteById(String id) {
        categoryRepository.deleteById(id);
        return "Xóa thành công";
    }

    @Override
    public CategoryResponse update(CategoryRequest data) {
        CategoryEntity categoryEntity=categoryRepository.findById(data.getId()).orElseThrow( ()-> new BusinessException("Danh mục không tồn tại."));
        categoryMapper.updateEntity(categoryEntity,data);
        if(data.getThumbnail()!=null) {
            String imgUrl = (String) cloudinaryService.upload(data.getThumbnail()).get("secure_url");
            categoryEntity.setThumbnail(imgUrl);
        }
        return categoryMapper.toResponse(categoryRepository.save(categoryEntity));
    }

    @Override
    public String deleteByListId(List<String> listId) {
        categoryRepository.deleteAllById(listId);
        return "Xóa thành công";
    }

}
