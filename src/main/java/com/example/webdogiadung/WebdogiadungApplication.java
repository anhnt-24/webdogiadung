package com.example.webdogiadung;

import com.example.webdogiadung.properties.CloudinaryProperties;
import com.example.webdogiadung.properties.JwtProperties;
import com.example.webdogiadung.properties.MailerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, CloudinaryProperties.class, MailerProperties.class})
public class WebdogiadungApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebdogiadungApplication.class, args);
	}

}
