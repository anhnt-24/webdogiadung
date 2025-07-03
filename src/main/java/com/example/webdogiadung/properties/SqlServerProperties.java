package com.example.webdogiadung.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Data
@Primary
@Component
@ConfigurationProperties(prefix = "sqlserver.datasource")
public class SqlServerProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}