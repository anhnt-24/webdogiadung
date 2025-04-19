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
import com.example.webdogiadung.service.interfa.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest data) {
        if (categoryRepository.findByName(data.getName()).isPresent()) {
            throw new BusinessException("Danh mục đã tồn tại.");
        }
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
    @Transactional
    public String deleteById(String id, boolean isDelete) {
        if (isDelete) {
            categoryRepository.deleteById(id);
        } else {
            categoryRepository.findById(id).ifPresent(categoryEntity -> {
                categoryEntity.setIsDeleted(true);
                categoryRepository.save(categoryEntity);
            });
        }
        return "Xóa thành công.";
    }

    @Override
    @Transactional
    public CategoryResponse update(CategoryRequest data) {
        CategoryEntity categoryEntity=categoryRepository.findById(data.getId()).orElseThrow( ()-> new BusinessException("Danh mục không tồn tại."));
        categoryMapper.updateEntity(categoryEntity,data);
        boolean nameExisted = categoryRepository.existsByNameAndIdNot(data.getName(), data.getId());
        if (nameExisted) {
            throw new BusinessException("Tên danh mục đã tồn tại.");
        }
        if (data.getThumbnail() != null && !data.getThumbnail().isEmpty()) {
            String imgUrl = (String) cloudinaryService.upload(data.getThumbnail()).get("secure_url");
            categoryEntity.setThumbnail(imgUrl);
        }
        return categoryMapper.toResponse(categoryRepository.save(categoryEntity));
    }

    @Override
    @Transactional
    public String deleteByListId(List<String> listId, boolean isDelete) {
        if (isDelete) {
            categoryRepository.deleteAllById(listId);

        } else {
            categoryRepository.findAllById(listId).forEach(categoryEntity -> {
                categoryEntity.setIsDeleted(true);
                categoryRepository.save(categoryEntity);
            });
        }
        return "Xóa thành công.";
    }


    @Override
    public String restore(List<String> listId) {
        categoryRepository.findAllById(listId).forEach(categoryEntity -> {
            categoryEntity.setIsDeleted(false);
            categoryRepository.save(categoryEntity);
        });
        return "Khôi phục thành công.";
    }
}
