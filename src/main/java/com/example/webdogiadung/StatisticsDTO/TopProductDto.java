package com.example.webdogiadung.StatisticsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
public class TopProductDto {

    @JsonProperty("product_key")
    private Integer productKey;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("category")
    private String category;

    @JsonProperty("selling_price")
    private BigDecimal sellingPrice;

    @JsonProperty("sold")
    private Integer sold;

    @JsonProperty("rating")
    private BigDecimal rating;

}