package com.example.webdogiadung.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="integration.cloudinary", ignoreUnknownFields = false)
@Getter
@Setter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CloudinaryProperties {
    String name;
    String apiKey;
    String apiSecret;
}
