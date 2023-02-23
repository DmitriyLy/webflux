package com.dmly.reactive.webflux.controller;

import com.dmly.reactive.webflux.jwt.JwtUtil;
import com.dmly.reactive.webflux.model.User;
import com.dmly.reactive.webflux.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private static final ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    private final ReactiveUserDetailsService userService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping(path = "login")
    public Mono<ResponseEntity> login(ServerWebExchange serverWebExchange) {
        return serverWebExchange
                .getFormData()
                .flatMap(this::performAuthorization);
    }

    private Mono<ResponseEntity<Object>> performAuthorization(MultiValueMap<String, String> credentials) {
        return userService.findByUsername(credentials.getFirst("username"))
                .cast(User.class)
                .map(userDetails -> checkUserCredentials(credentials, userDetails));
    }

    private ResponseEntity<Object> checkUserCredentials(MultiValueMap<String, String> credentials, User userDetails) {
        if (authService.checkUserPassword(credentials, userDetails)) {
            return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
        }

        return UNAUTHORIZED;
    }

}
