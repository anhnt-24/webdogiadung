package com.example.webdogiadung.config.security;

import com.example.webdogiadung.entity.psql.AccountEntity;
import com.example.webdogiadung.repository.psql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity accountEntity=accountRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
        return new UserDetailsCustom(accountEntity);
    }
}
