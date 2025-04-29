package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.constants.OrderItemStatus;
import com.example.webdogiadung.constants.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {
    String id;

    String orderId;

    String productId;

    Long quantity;

    Double totalPrice;

    OrderItemStatus orderStatus;
}
