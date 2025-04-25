package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.constants.MethodPayment;
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
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String id;

    @JsonProperty("order_code")
    String orderCode;

    @JsonProperty("client_id")
    @NotBlank(message = "client id is required")
    String clientId;

    @JsonProperty("delivery_fee")
    Double deliveryFee;

    @JsonProperty("total_amount")
    Double totalAmount;

    @JsonProperty("is_active")
    Boolean isActive;
}
