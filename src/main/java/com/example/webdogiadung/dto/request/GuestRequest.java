package com.example.webdogiadung.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuestRequest {
    String id;
    String name;
    String phone;
    Boolean unread;
    String color;

}
