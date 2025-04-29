package com.example.webdogiadung.dto.response;

import com.example.webdogiadung.constants.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String id;
    String name;
    String phone;
    String email;
    Role role;
    String avatarUrl;
    String token;
}
