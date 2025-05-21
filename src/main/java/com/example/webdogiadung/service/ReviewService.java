package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ReviewRequest;
import com.example.webdogiadung.dto.request.search.ReviewSearchRequest;
import com.example.webdogiadung.dto.response.ReviewResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.ProductEntity;
import com.example.webdogiadung.entity.ReviewEntity;
import com.example.webdogiadung.exception.DataNotFoundException;
import com.example.webdogiadung.mapper.ReviewMapper;
import com.example.webdogiadung.repository.ProductRepository;
import com.example.webdogiadung.repository.ReviewRepository;
import com.example.webdogiadung.service.interfa.ReviewServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements ReviewServiceInterface {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ProductRepository productRepository;

    @Override
    public ReviewResponse create(ReviewRequest data) {
        ReviewEntity entity = reviewMapper.toEntity(data);
        ProductEntity product = productRepository.findById(data.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy sản phẩm."));
        entity.setProduct(product);
        return reviewMapper.toResponse(reviewRepository.save(entity));
    }

    @Override
    public PagingResponse<ReviewResponse> getAll(ReviewSearchRequest request) {
        Page<ReviewEntity> reviewEntityPage = reviewRepository.findAll(request.specification(), request.getPaging().pageable());
        return PagingResponse.<ReviewResponse>builder()
                .contents(reviewMapper.toResponse(reviewEntityPage.getContent()))
                .paging(new PageableData()
                        .setPageNumber(reviewEntityPage.getNumber())
                        .setPageSize(reviewEntityPage.getSize())
                        .setTotalPage(reviewEntityPage.getTotalPages())
                        .setTotalRecord(reviewEntityPage.getTotalElements()))
                .build();
    }

    @Override
    public ReviewResponse getById(String id) {
        return reviewMapper.toResponse(reviewRepository.findById(id).orElse(null));
    }

    @Override
    public String deleteById(String id, boolean isDelete) {
        return reviewRepository.findById(id).map(review -> {
            reviewRepository.save(review);
            return "Xóa thành công.";
        }).orElse("Không tìm thấy đánh giá.");
    }

    @Override
    public ReviewResponse update(ReviewRequest data) {
        ReviewEntity entity = reviewRepository.findById(data.getId())
                .orElseThrow(() -> new DataNotFoundException("Đánh giá không tồn tại."));
        reviewMapper.updateEntity(entity, data);
        return reviewMapper.toResponse(reviewRepository.save(entity));
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        listId.forEach(id -> deleteById(id, isDelete));
        return "Đã xử lý " + listId.size() + " đánh giá.";
    }
}
