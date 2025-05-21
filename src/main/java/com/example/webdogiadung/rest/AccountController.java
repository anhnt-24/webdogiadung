package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.AccountRequest;
import com.example.webdogiadung.dto.request.GoogleAuthenRequest;
import com.example.webdogiadung.dto.response.AccountResponse;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.LoginResponse;
import com.example.webdogiadung.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("login")
    public ApiResponse<LoginResponse> login(@RequestBody AccountRequest accountRequest, HttpServletResponse response) {
        return ApiResponse.<LoginResponse>builder()
                .status(Status.OK)
                .data(
                        accountService.login(accountRequest, response)
                )
                .build();
    }
    @PostMapping("logout")
    public ApiResponse<String> logout(HttpServletResponse response) {
        accountService.logout(response);
        return ApiResponse.<String>builder()
                .status(Status.OK)
                .data("Đăng xuất thành công.")
                .build();

    }
    @GetMapping("refresh")
    public ApiResponse<LoginResponse> refresh(HttpServletRequest request) {
        return ApiResponse.<LoginResponse>builder()
                .status(Status.OK)
                .data(
                        accountService.refresh(request)
                )
                .build();

    }
    @GetMapping("myinfo")
    public ApiResponse<AccountResponse> getMyInfo() {
        return ApiResponse.<AccountResponse>builder()
                .status(Status.OK)
                .data(
                        accountService.getMyAccount()
                )
                .build();

    }

    @PostMapping("/google/login")
    public ApiResponse<LoginResponse> googleLogin(@RequestBody GoogleAuthenRequest googleAuthenRequest, HttpServletResponse response) {
        return ApiResponse.<LoginResponse>builder()
                .status(Status.OK)
                .data(
                        accountService.verifyGoogleToken(googleAuthenRequest,response)
                )
                .build();

    }






}
