//package com.example.webdogiadung.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import web.examination.CostumeForRent.entity.AccountEntity;
//import web.examination.CostumeForRent.repository.AccountRepository;
//import web.examination.CostumeForRent.service.AccountService;
//
//@Component
//@RequiredArgsConstructor
//public class UserDetailsServiceCustom implements UserDetailsService {
//
//    private final AccountRepository accountRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AccountEntity accountEntity=accountRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
//        return new UserDetailsCustom(accountEntity);
//    }
//}
