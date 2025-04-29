package com.example.webdogiadung.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuestResponse {
    String id;
    String name;
    String phone;
    Boolean unread;
    String color;
}
