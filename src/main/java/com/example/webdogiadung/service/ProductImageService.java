package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ProductImageRequest;
import com.example.webdogiadung.dto.response.ProductImageResponse;
import com.example.webdogiadung.entity.psql.ProductEntity;
import com.example.webdogiadung.entity.psql.ProductImage;
import com.example.webdogiadung.repository.psql.ProductImageRepository;
import com.example.webdogiadung.repository.psql.ProductRepository;
import com.example.webdogiadung.service.interfa.ProductImageInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageService implements ProductImageInterface {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public void saveProductImage(ProductImageRequest request) {
        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        String imageUrl = (String) cloudinaryService.upload(request.getImage()).get("secure_url");

        ProductImage image = new ProductImage();
        image.setId(UUID.randomUUID().toString());
        image.setProductEntity(product);
        image.setName(request.getImage().getName());
        image.setUrl(imageUrl);

        productImageRepository.save(image);
    }

    @Override
    public List<ProductImageResponse> getAllProductImagesByProductId(String productId) {
        return productImageRepository.findByProductEntityId(productId)
                .stream()
                .map(image -> ProductImageResponse.builder()
                        .id(image.getId())
                        .productId(image.getProductEntity() != null ? image.getProductEntity().getId() : null)
                        .name(image.getName())
                        .url(image.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductImage(String imageId) {
        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ảnh"));
        image.setProductEntity(null);
        productImageRepository.save(image);
    }
}
