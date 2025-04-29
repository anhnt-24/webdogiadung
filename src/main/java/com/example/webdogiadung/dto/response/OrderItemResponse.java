package com.example.webdogiadung.dto.response;

import com.example.webdogiadung.constants.OrderItemStatus;
import com.example.webdogiadung.constants.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {
    String id;
    ProductResponse product;
    Long quantity;
    Double totalPrice;
    OrderItemStatus orderStatus;
}
