package com.example.webdogiadung.jwt;

import com.example.webdogiadung.properties.JwtProperties;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


@Data
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private static final String AUTHORITIES_KEY = "auth";

    public String createAccessToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer(authentication.getPrincipal().toString())
                .issueTime(new Date())
                .claim(AUTHORITIES_KEY,authorities)
                .expirationTime(new Date(Instant.now().plus(jwtProperties.getExpireTime(), ChronoUnit.SECONDS).toEpochMilli()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        JWSSigner signer= null;
        try {
            signer = new MACSigner(jwtProperties.getSignerKey().getBytes());
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            jwsObject.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
      }
    public String createRefreshToken(Authentication authentication) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS384);
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer(authentication.getPrincipal().toString())
                .claim(AUTHORITIES_KEY,authorities)
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(jwtProperties.getRefreshTime(), ChronoUnit.SECONDS).toEpochMilli()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        JWSSigner signer= null;
        try {
            signer = new MACSigner(jwtProperties.getSignerKey().getBytes());
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            jwsObject.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        String token=jwsObject.serialize();
//        RefreshTokenEntity refreshTokenEntity=new RefreshTokenEntity();
        return token;
    }

      public JWTClaimsSet verify(String token)  {
          try {
              SignedJWT signedJWT=SignedJWT.parse(token);
              var claimSet= signedJWT.getJWTClaimsSet();
              JWSVerifier jwsVerifier=new MACVerifier(jwtProperties.getSignerKey());
              if(!signedJWT.verify(jwsVerifier)){
                  throw new RuntimeException("Token không hợp lệ");
              }
              Date expirationTime = claimSet.getExpirationTime();
              if (expirationTime == null || expirationTime.before(new Date())) {
                  throw new RuntimeException("Token đã hết hạn");
              }
              return claimSet;

          } catch (ParseException | JOSEException e) {
              throw new RuntimeException(e);
          }
      }
      public Authentication getAuthentication(String token){
          JWTClaimsSet claims=this.verify(token);
          Collection<? extends GrantedAuthority> authorities = Arrays
                  .stream(claims.getClaims().get(AUTHORITIES_KEY).toString().split(","))
                  .filter(auth -> !auth.trim().isEmpty())
                  .map("ROLE_"::concat)
                  .map(SimpleGrantedAuthority::new)
                  .collect(Collectors.toList());
          User principal = new User(claims.getIssuer(), "", authorities);
          return new UsernamePasswordAuthenticationToken(principal, token, authorities);
      }

}
