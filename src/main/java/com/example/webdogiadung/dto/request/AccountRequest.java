package com.example.webdogiadung.dto.request;

import com.example.webdogiadung.constants.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {
    String id;
    String name;
    String phone;
    String email;
    String password;
    Role role;
    MultipartFile avatar;
}
