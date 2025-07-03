package com.example.webdogiadung.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Primary
@Component
@ConfigurationProperties(prefix = "postgres.datasource")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PsqlProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}