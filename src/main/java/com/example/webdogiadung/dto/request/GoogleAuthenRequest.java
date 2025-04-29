package com.example.webdogiadung.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoogleAuthenRequest {
    String ggToken;
    String name;
    String email;
    String avatar;
}
