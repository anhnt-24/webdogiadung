package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ProductDescriptionRequest;
import com.example.webdogiadung.dto.response.ProductDescriptionResponse;
import com.example.webdogiadung.entity.ProductDescriptionEntity;
import com.example.webdogiadung.entity.ProductEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.repository.ProductDescriptionRepository;
import com.example.webdogiadung.repository.ProductRepository;
import com.example.webdogiadung.service.interfa.ProductDescriptionServiceInterface;
import com.example.webdogiadung.mapper.ProductDescriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDescriptionService implements ProductDescriptionServiceInterface {

    private final ProductRepository productRepository;
    private final ProductDescriptionRepository productDescriptionRepository;
    private final ProductDescriptionMapper productDescriptionMapper;

    public ProductDescriptionResponse getByProductId(String productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(()->new BusinessException("Sản phẩm không tồn tại."));
        ProductDescriptionEntity productDescriptionEntity = productDescriptionRepository.findByProduct(productEntity).orElse(null);
        if(productDescriptionEntity == null) {
            productDescriptionEntity =new ProductDescriptionEntity();
            productDescriptionEntity.setProduct(productEntity);
            productDescriptionRepository.save(productDescriptionEntity);
        }

        return productDescriptionMapper.toResponse(productDescriptionEntity);
    }


    @Override
    public ProductDescriptionResponse get(String id) {
        ProductDescriptionEntity productDescriptionEntity = productDescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product description not found"));

        return productDescriptionMapper.toResponse(productDescriptionEntity);
    }

    public ProductDescriptionResponse update(ProductDescriptionRequest request) {
        ProductDescriptionEntity productDescriptionEntity = productDescriptionRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product description not found"));

        productDescriptionMapper.updateEntity(productDescriptionEntity, request);
        productDescriptionRepository.save(productDescriptionEntity);

        return productDescriptionMapper.toResponse(productDescriptionEntity);
    }

    @Override
    public void delete(String id) {
        ProductDescriptionEntity productDescriptionEntity = productDescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product description not found"));

        productDescriptionRepository.delete(productDescriptionEntity);
    }
}
