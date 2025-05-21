package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.constants.MethodPayment;
import com.example.webdogiadung.constants.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String id;

    String orderCode;

    String clientId;

    Double deliveryFee;

    Double totalAmount;

    String deliveryAddress;

    OrderStatus status;

    MethodPayment methodPayment;
}
