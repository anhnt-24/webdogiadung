package com.example.webdogiadung.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientRequest {

    @JsonProperty("name")
    @NotBlank(message = "name is required")
    String name;

    @JsonProperty("email")
    @NotBlank(message = "email is required")
    String email;

    @JsonProperty("phone")
    @NotBlank(message = "phone is required")
    String phone;

    @JsonProperty("join_date")
    @NotBlank(message = "join date is required")
    Instant joinDate;

    @JsonProperty("total_spent")
    Double totalSpent;
}
