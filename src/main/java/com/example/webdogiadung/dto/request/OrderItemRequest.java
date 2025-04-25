package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.entity.Constants.OrderStatus;
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

    @JsonProperty("order_id")
    String orderId;

    @JsonProperty("product_id")
    String productId;

    Long quantity;

    @JsonProperty("unit_price")
    Double unitPrice;

    @JsonProperty("total_price")
    Double totalPrice;

    @JsonProperty("delivery_address")
    String deliveryAddress;

    @JsonProperty("status")
    OrderStatus orderStatus;
}
