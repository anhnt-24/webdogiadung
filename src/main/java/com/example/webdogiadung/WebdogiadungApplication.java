package com.example.webdogiadung;

import com.example.webdogiadung.constants.Role;
import com.example.webdogiadung.entity.AccountEntity;
import com.example.webdogiadung.properties.CloudinaryProperties;
import com.example.webdogiadung.properties.JwtProperties;
import com.example.webdogiadung.properties.MailerProperties;
import com.example.webdogiadung.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, CloudinaryProperties.class, MailerProperties.class})
@RequiredArgsConstructor
public class WebdogiadungApplication {
	private final AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(WebdogiadungApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return args -> {
			if(!accountRepository.findByEmail("admin@example.com").isPresent()) {
				AccountEntity accountEntity = new AccountEntity();
				accountEntity.setEmail("admin@example.com");
				accountEntity.setPassword("admin");
				accountEntity.setRole(Role.ADMIN);
				accountEntity.setName("admin");
				accountRepository.save(accountEntity);
			}

		};
	}
}
