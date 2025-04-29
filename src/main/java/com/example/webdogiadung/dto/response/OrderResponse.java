package com.example.webdogiadung.dto.response;

import com.example.webdogiadung.constants.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
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

}
