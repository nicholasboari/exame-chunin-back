package com.nicholasboari.examechunin.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nicholasboari.examechunin.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {


    public String generateToken(User user) {
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return JWT.create().withIssuer("Vehicles")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("roles", authorities)
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("secret"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("Vehicles")
                .build().verify(token).getSubject();
    }
}
