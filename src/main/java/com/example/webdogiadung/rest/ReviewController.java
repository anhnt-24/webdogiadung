package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.ReviewRequest;
import com.example.webdogiadung.dto.request.search.ReviewSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ReviewResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review/")
@RequiredArgsConstructor
public class ReviewController implements BaseControllerInterface<ReviewSearchRequest, ReviewRequest, ReviewResponse, String> {

    private final ReviewService reviewService;

    @Override
    @PostMapping("create")
    public ApiResponse<ReviewResponse> create(@RequestBody ReviewRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .status(Status.OK)
                .data(reviewService.create(request))
                .build();
    }

    @Override
    @PutMapping("update")
    public ApiResponse<ReviewResponse> update(@RequestBody ReviewRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .status(Status.UPDATED)
                .data(reviewService.update(request))
                .build();
    }

    @Override
    public ApiResponse<PagingResponse<ReviewResponse>> getAll(ReviewSearchRequest request) {
        return ApiResponse.<PagingResponse<ReviewResponse>>builder()
                .status(Status.OK)
                .data(reviewService.getAll(request))
                .build();
    }

    @Override
    public ApiResponse<ReviewResponse> getById(String id) {
        return ApiResponse.<ReviewResponse>builder()
                .status(Status.OK)
                .data(reviewService.getById(id))
                .build();
    }

    @Override
    public ApiResponse<String> deleteById(@PathVariable String id,
                                          @RequestParam(defaultValue = "true") boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(reviewService.deleteById(id, isDeleted))
                .build();
    }

    @Override
    public ApiResponse<String> deleteByListId(@RequestBody List<String> listId,
                                              @RequestParam(defaultValue = "true") boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(reviewService.deleteByListId(listId, isDeleted))
                .build();
    }
}
