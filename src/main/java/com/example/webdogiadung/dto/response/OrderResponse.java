package com.example.webdogiadung.dto.response;

import com.example.webdogiadung.constants.MethodPayment;
import com.example.webdogiadung.constants.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    ClientResponse client;
    String orderCode;
    Double deliveryFee;
    Double totalAmount;
    Instant createdDate;
    OrderStatus status;
    String deliveryAddress;
    MethodPayment methodPayment;
}
