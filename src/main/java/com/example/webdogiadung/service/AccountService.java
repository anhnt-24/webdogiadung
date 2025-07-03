package com.example.webdogiadung.service;

import com.example.webdogiadung.Utils.CookieUtil;
import com.example.webdogiadung.Utils.RandomCodeGenerate;
import com.example.webdogiadung.Utils.SecurityUtils;
import com.example.webdogiadung.config.security.UserDetailsCustom;
import com.example.webdogiadung.constants.Role;
import com.example.webdogiadung.dto.request.AccountRequest;
import com.example.webdogiadung.dto.request.GoogleAuthenRequest;
import com.example.webdogiadung.dto.response.AccountResponse;
import com.example.webdogiadung.dto.response.LoginResponse;
import com.example.webdogiadung.entity.psql.AccountEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.jwt.JwtProvider;
import com.example.webdogiadung.mapper.AccountMapper;
import com.example.webdogiadung.repository.psql.AccountRepository;
import com.example.webdogiadung.service.interfa.AccountServiceInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountServiceInterface {
    private final JwtProvider jwtProvider;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final String REFRESH_TOKEN="refresh_token";

    @Override
    public LoginResponse create(AccountRequest accountRequest, HttpServletResponse response) {
        AccountEntity accountEntity = accountMapper.toEntity(accountRequest);
        accountEntity.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        accountRepository.save(accountEntity);
        final var authenticationToken = new UsernamePasswordAuthenticationToken(
                accountRequest.getEmail(),
                accountRequest.getPassword()
        );
        final var authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String refreshToken=jwtProvider.createRefreshToken(authentication);
        CookieUtil.addCookie(response,REFRESH_TOKEN,refreshToken,true,true);
        String token=jwtProvider.createAccessToken(authentication);
        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public LoginResponse login(AccountRequest accountRequest, HttpServletResponse response) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(
                accountRequest.getEmail(),
                accountRequest.getPassword()
        );
        final var authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String refreshToken=jwtProvider.createRefreshToken(authentication);
        CookieUtil.addCookie(response,REFRESH_TOKEN,refreshToken,true,true);
        String token=jwtProvider.createAccessToken(authentication);
        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AccountResponse update(AccountRequest account) {
        AccountEntity accountEntity = accountRepository.findById(account.getId()).orElseThrow(()->new BusinessException("Tài khoản không tồn tại."));
        accountMapper.updateEntity(accountEntity, account);
        return accountMapper.toResponse(accountEntity);
    }

    @Override
    public AccountResponse getMyAccount() {
        String email= SecurityUtils.getCurrentUser();
        System.out.println(email);
        return accountMapper.toResponse(accountRepository.findByEmail(email).orElse(null));
    }

    @Override
    public LoginResponse refresh(HttpServletRequest request) {
        String refreshToken=CookieUtil.getCookieValue(request,REFRESH_TOKEN).orElseThrow(()->new BusinessException("Phiên đăng nhập hết hạn"));
        Authentication authentication=jwtProvider.getAuthenticationForRefresh(refreshToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return LoginResponse.builder().token(jwtProvider.createAccessToken(authentication)).build();
    }


    @Override
    public AccountResponse getAccountById(String id) {
        return null;
    }

    @Override
    public void logout(HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        CookieUtil.deleteCookie(response,REFRESH_TOKEN);
    }
    @Override
    @Transactional
    public LoginResponse verifyGoogleToken(GoogleAuthenRequest request, HttpServletResponse  response) {
        try {
            if(FirebaseAuth.getInstance().verifyIdToken(request.getGgToken())!=null){
                AccountEntity accountEntity= accountRepository.findByEmail(request.getEmail()).orElse(null);
                if(accountEntity==null){
                    accountEntity=new AccountEntity();
                    accountEntity.setAvatarUrl(request.getAvatar());
                    accountEntity.setEmail(request.getEmail());
                    accountEntity.setPassword(passwordEncoder.encode(RandomCodeGenerate.generatePassword(8)));
                    accountEntity.setName(request.getName());
                    accountEntity.setRole(Role.ADMIN);
                    accountRepository.save(accountEntity);
                }
                UserDetails userDetails = new UserDetailsCustom(accountEntity);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String refreshToken = jwtProvider.createRefreshToken(authentication);
                CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken, true, true);
                String token=jwtProvider.createAccessToken(authentication);
                return LoginResponse.builder().token(token).build();

            };

        } catch (FirebaseAuthException e) {
            throw new BusinessException("Token không hợp lệ");
        }
        return null;
    }
}
