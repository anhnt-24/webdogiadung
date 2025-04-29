package com.example.webdogiadung.service.interfa;

import com.example.webdogiadung.dto.request.AccountRequest;
import com.example.webdogiadung.dto.request.GoogleAuthenRequest;
import com.example.webdogiadung.dto.response.AccountResponse;
import com.example.webdogiadung.dto.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountServiceInterface {
    LoginResponse create(AccountRequest accountRequest, HttpServletResponse response);

    LoginResponse login(AccountRequest accountRequest, HttpServletResponse response);

    AccountResponse update(AccountRequest account);

    AccountResponse getMyAccount();

    LoginResponse verifyGoogleToken(GoogleAuthenRequest request, HttpServletResponse  response);

    LoginResponse refresh(HttpServletRequest request);

    AccountResponse getAccountById(String id) ;

    void logout(HttpServletResponse request);

}
