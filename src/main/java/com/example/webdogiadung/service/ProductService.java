package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ProductRequest;
import com.example.webdogiadung.dto.request.search.ProductSearchRequest;
import com.example.webdogiadung.dto.response.ProductResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.psql.CategoryEntity;
import com.example.webdogiadung.entity.psql.ProductEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.mapper.ProductMapper;
import com.example.webdogiadung.repository.psql.CategoryRepository;
import com.example.webdogiadung.repository.psql.ProductRepository;
import com.example.webdogiadung.service.interfa.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional
    public ProductResponse create(ProductRequest data) {
        if (productRepository.findByName(data.getName()).isPresent()) {
            throw new BusinessException("Sản phẩm đã tồn tại.");
        }
        CategoryEntity categoryEntity=categoryRepository.findById(data.getCategoryId()).orElseThrow(()->new BusinessException("Danh mục không tồn tại."));
        ProductEntity productEntity = productMapper.toEntity(data);
        productEntity.setCategory(categoryEntity);
        String imgUrl = (String) cloudinaryService.upload(data.getThumbnailFile()).get("secure_url");
        productEntity.setThumbnail(imgUrl);
        return productMapper.toResponse(productRepository.save(productEntity));
    }

    @Override
    public PagingResponse<ProductResponse> getAll(ProductSearchRequest request) {
        Page<ProductEntity> productEntityPage = productRepository.findAll(request.specification(), request.getPaging().pageable());
        return PagingResponse.<ProductResponse>builder()
                .contents(productMapper.toResponse(productEntityPage.getContent()))
                .paging(new PageableData()
                        .setPageNumber(productEntityPage.getNumber())
                        .setPageSize(productEntityPage.getSize())
                        .setTotalPage(productEntityPage.getTotalPages())
                        .setTotalRecord(productEntityPage.getTotalElements()))
                .build();
    }
    public List<ProductResponse> getAllForExport() {
        return productMapper.toResponse(productRepository.findAll());
    }

    @Override
    public ProductResponse getById(String id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        ProductResponse productResponse = productMapper.toResponse(productEntity);
        productResponse.setCategoryId(productEntity.getCategory().getId());
        return productResponse;
    }

    @Override
    @Transactional
    public String deleteById(String id, boolean isDelete) {
        Optional<ProductEntity> productEntityOpt = productRepository.findById(id);
        if (!productEntityOpt.isPresent()) {
            throw new BusinessException("Sản phẩm không tồn tại.");
        }
        ProductEntity productEntity = productEntityOpt.get();
        if (isDelete) {
            productRepository.deleteById(id);
        } else {
            productEntity.setIsDeleted(true);
            productRepository.save(productEntity);
        }
        return "Xóa thành công.";
    }

    @Override
    @Transactional
    public ProductResponse update(ProductRequest data) {
        ProductEntity productEntity = productRepository.findById(data.getId())
                .orElseThrow(() -> new BusinessException("Sản phẩm không tồn tại."));
        productMapper.updateEntity(productEntity, data);
        boolean nameExisted = productRepository.existsByNameAndIdNot(data.getName(), data.getId());
        if (nameExisted) {
            throw new BusinessException("Tên sản phẩm đã tồn tại.");
        }
        if (data.getThumbnailFile() != null && !data.getThumbnailFile().isEmpty()) {
            String imgUrl = (String) cloudinaryService.upload(data.getThumbnailFile()).get("secure_url");
            productEntity.setThumbnail(imgUrl);
        }
        return productMapper.toResponse(productRepository.save(productEntity));
    }

    @Override
    @Transactional
    public String deleteByListId(List<String> listId, boolean isDelete) {
        if (isDelete) {
            productRepository.deleteAllById(listId);
        } else {
            productRepository.findAllById(listId).forEach(productEntity -> {
                productEntity.setIsDeleted(true);
                productRepository.save(productEntity);
            });
        }
        return "Xóa thành công.";
    }



    @Override
    public String restore(List<String> listId) {
        List<ProductEntity> productEntityList=productRepository.findAllById(listId);
        productEntityList.stream().forEach(productEntity -> {
                    productEntity.setIsDeleted(false);
                    productRepository.save(productEntity);});
        return "Khôi phục thành công.";
    }
}
