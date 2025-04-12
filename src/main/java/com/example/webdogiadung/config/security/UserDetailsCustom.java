//package com.example.webdogiadung.config.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import web.examination.CostumeForRent.entity.AccountEntity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@AllArgsConstructor
//public class UserDetailsCustom implements UserDetails {
//
//    private AccountEntity accountEntity;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        this.accountEntity.getRoles().stream().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return accountEntity.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return accountEntity.getUsername();
//    }
//}
