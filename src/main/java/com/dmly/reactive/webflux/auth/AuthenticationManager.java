package com.dmly.reactive.webflux.auth;

import com.dmly.reactive.webflux.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        String authToken = authentication.getCredentials().toString();

        String username = null;

        try {
            username = jwtUtil.extractUsername(authToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (username != null && jwtUtil.validateToken(authToken)) {
            Claims claims = jwtUtil.getClaimsFromToken(authToken);

            List<String> roles = claims.get("role", List.class);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            var authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

            return Mono.just(authenticationToken);

        }

        return Mono.empty();
    }
}
