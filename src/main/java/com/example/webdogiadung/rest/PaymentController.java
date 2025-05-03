package com.example.webdogiadung.rest;

import com.example.webdogiadung.config.VnpayConfiguration;
import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.service.OrderService;
import com.example.webdogiadung.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.example.webdogiadung.config.VnpayConfiguration.hmacSHA512;

@RestController
@RequestMapping("/api/v1/payment/")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    @PostMapping("create")
    public ApiResponse<String> create(@RequestBody OrderRequest orderRequest, HttpServletRequest request) throws UnsupportedEncodingException {
        return ApiResponse.<String>builder()
                .status(Status.CREATED)
                .data(paymentService.createPayment(orderRequest,request))
                .build();

    }
    @PostMapping("result")
    public ApiResponse<String> paymentResult(HttpServletRequest request) {
        return ApiResponse.<String>builder()
                .status(Status.OK)
                .data(paymentService.checkPayment(request))
                .build();
    }


}
