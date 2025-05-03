package com.example.webdogiadung.service;

import com.example.webdogiadung.Utils.RandomCodeGenerate;
import com.example.webdogiadung.constants.MethodPayment;
import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.OrderEntity;
import com.example.webdogiadung.entity.ProductEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.exception.DataNotFoundException;
import com.example.webdogiadung.mapper.OrderMapper;
import com.example.webdogiadung.repository.ClientRepository;
import com.example.webdogiadung.repository.OrderRepository;
import com.example.webdogiadung.service.interfa.OrderServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;
    private final PaymentService paymentService;
    @Override
    public OrderResponse create(OrderRequest data) {

        var clientEntity = clientRepository.findById(data.getClientId())
                .orElseThrow(() -> new DataNotFoundException("client id not found"));
        OrderEntity orderEntity = orderMapper.toEntity(data);
        orderEntity.setClient(clientEntity);
        orderEntity.setOrderCode(RandomCodeGenerate.generateOrderCode());
        return orderMapper.toResponse(orderRepository.save(orderEntity));
    }
    @Override
    public PagingResponse<OrderResponse> getAll(OrderSearchRequest request) {
        Page<OrderEntity> orderEntityPage=orderRepository.findAll(request.specification(),request.getPaging().pageable());
        return PagingResponse.<OrderResponse>builder()
                .contents(orderMapper.toResponse(orderEntityPage.getContent()))
                .paging(new PageableData()
                        .setPageNumber(orderEntityPage.getNumber())
                        .setPageSize(orderEntityPage.getSize())
                        .setTotalPage(orderEntityPage.getTotalPages())
                        .setTotalRecord(orderEntityPage.getTotalElements()))
                .build();
    }

    @Override
    public OrderResponse getById(String id) {
        return orderMapper.toResponse(orderRepository.findById(id).orElse(null));
    }

    @Override
    public String deleteById(String id, boolean isDelete) {
        if (isDelete) {
            orderRepository.deleteById(id);
        } else {
            orderRepository.findById(id).ifPresent(orderEntity -> {
                orderRepository.save(orderEntity);
            });
        }
        return "Xóa thành công.";
    }

    @Override
    public OrderResponse update(OrderRequest data) {
        OrderEntity orderEntity = orderRepository.findById(data.getId())
                .orElseThrow(() -> new DataNotFoundException("Đơn hàng không tồn tại."));
        orderMapper.updateEntity(orderEntity, data);
        orderRepository.save(orderEntity);
        return orderMapper.toResponse(orderRepository.save(orderEntity));
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        return "";
    }


}
