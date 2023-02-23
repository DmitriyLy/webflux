package com.dmly.reactive.webflux.jwt;

import com.dmly.reactive.webflux.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationTime;

    public Object generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("role", List.of(user.getRole()));

        Date creationDate = convertDate(LocalDateTime.now());
        Date expirationDate = convertDate(LocalDateTime.now().plusSeconds(Long.parseLong(expirationTime)));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                //.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    private Date convertDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public String extractUsername(String authToken) {
        return getClaimsFromToken(authToken)
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        return getClaimsFromToken(authToken)
                .getExpiration()
                .after(new Date());
    }

    public Claims getClaimsFromToken(String authToken) {
        String key = Base64.getEncoder().encodeToString(secret.getBytes());

        return Jwts.parserBuilder()
                //.setSigningKey(key)
                .build()
                .parseClaimsJwt(authToken)
                .getBody();
    }
}
