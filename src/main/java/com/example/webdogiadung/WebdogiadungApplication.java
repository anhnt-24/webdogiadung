package com.example.webdogiadung;

import com.example.webdogiadung.constants.Role;
import com.example.webdogiadung.entity.psql.AccountEntity;
import com.example.webdogiadung.properties.CloudinaryProperties;
import com.example.webdogiadung.properties.JwtProperties;
import com.example.webdogiadung.properties.MailerProperties;
import com.example.webdogiadung.properties.PsqlProperties;
import com.example.webdogiadung.repository.psql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, CloudinaryProperties.class, MailerProperties.class})
@RequiredArgsConstructor
public class WebdogiadungApplication {
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(WebdogiadungApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return args -> {
			if(!accountRepository.findByEmail("admin@gmail.com").isPresent()) {
				AccountEntity accountEntity = new AccountEntity();
				accountEntity.setEmail("admin@gmail.com");
				accountEntity.setPassword(passwordEncoder.encode("123456"));
				accountEntity.setRole(Role.ADMIN);
				accountEntity.setName("admin");
				accountRepository.save(accountEntity);
			}

		};
	}
}
