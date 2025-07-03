package com.example.webdogiadung.StatisticsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TopCustomerDto {

    @JsonProperty("customer_key")
    private Integer customerKey;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("total_spent")
    private BigDecimal totalSpent;

    @JsonProperty("num_order")
    private Integer numOrder;
}
