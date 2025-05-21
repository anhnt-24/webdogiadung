package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.ReviewRequest;
import com.example.webdogiadung.dto.request.search.ReviewSearchRequest;
import com.example.webdogiadung.dto.response.ReviewResponse;
import com.example.webdogiadung.dto.response.SearchResponse;

import java.util.List;

public interface ReviewServiceInterface extends BaseServiceInterface<ReviewSearchRequest, ReviewRequest, ReviewResponse, String> {
}
