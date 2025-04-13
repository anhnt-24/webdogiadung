package com.example.webdogiadung.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest() {
    @JsonProperty("name")
    @NotBlank(message = "name is required")
    static String name;
}
