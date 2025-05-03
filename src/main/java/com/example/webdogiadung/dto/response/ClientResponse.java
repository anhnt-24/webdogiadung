package com.example.webdogiadung.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientResponse {
    String id;
    String name;
    String email;
    String phone;
    Double totalSpent;
    Instant createdDate;
    Long numOrder;
}
