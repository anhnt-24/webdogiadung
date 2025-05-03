package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.OrderItemRequest;
import com.example.webdogiadung.dto.request.search.OrderItemSearchRequest;
import com.example.webdogiadung.dto.response.OrderItemResponse;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.OrderEntity;
import com.example.webdogiadung.entity.OrderItemEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.exception.DataNotFoundException;
import com.example.webdogiadung.mapper.OrderItemMapper;
import com.example.webdogiadung.repository.OrderItemRepository;
import com.example.webdogiadung.repository.OrderRepository;
import com.example.webdogiadung.repository.ProductRepository;
import com.example.webdogiadung.service.interfa.OrderItemServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService implements OrderItemServiceInterface {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponse create(OrderItemRequest data) {
        var productEntity = productRepository.findById(data.getProductId())
                .orElseThrow(() -> new DataNotFoundException("product not found"));
        var orderEntity = orderRepository.findById(data.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("order not found"));

        if(productEntity.getStock()<data.getQuantity()){
            throw new BusinessException("Không đủ số lượng sản phẩm.");
        }
        productEntity.setStock(productEntity.getStock()-data.getQuantity());
        productRepository.save(productEntity);
        OrderItemEntity orderItemEntity = orderItemMapper.toEntity(data);
        orderItemEntity.setProduct(productEntity);
        orderItemEntity.setOrderEntity(orderEntity);
        return orderItemMapper.toResponse(orderItemRepository.save(orderItemEntity));
    }

    @Override
    public PagingResponse<OrderItemResponse> getAll(OrderItemSearchRequest request) {
        Page<OrderItemEntity> orderItemEntityPage = orderItemRepository.findAll(request.specification(),request.getPaging().pageable());
        return PagingResponse.<OrderItemResponse>builder()
                .contents(orderItemMapper.toResponse(orderItemEntityPage.getContent()))
                .paging(new PageableData()
                        .setPageNumber(orderItemEntityPage.getNumber())
                        .setPageSize(orderItemEntityPage.getSize())
                        .setTotalPage(orderItemEntityPage.getTotalPages())
                        .setTotalRecord(orderItemEntityPage.getTotalElements()))
                .build();
    }

    @Override
    public OrderItemResponse getById(String id) {
        return orderItemMapper.toResponse(orderItemRepository.findById(id).orElse(null));
    }

    @Override
    public String deleteById(String id, boolean isDelete) {
        if (isDelete) {
            orderItemRepository.deleteById(id);
        } else {
            orderItemRepository.findById(id).ifPresent(orderItemEntity -> {
                orderItemRepository.save(orderItemEntity);
            });
        }
        return "Xóa thành công.";
    }

    @Override
    public OrderItemResponse update(OrderItemRequest data) {
        var orderItemEntity = orderItemRepository.findById(data.getId())
                .orElseThrow(() -> new DataNotFoundException("Sản phẩm không tồn tại."));
        orderItemMapper.updateEntity(orderItemEntity,data);
        orderItemRepository.save(orderItemEntity);
        return orderItemMapper.toResponse(orderItemRepository.save(orderItemEntity));
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        return "";
    }

}
