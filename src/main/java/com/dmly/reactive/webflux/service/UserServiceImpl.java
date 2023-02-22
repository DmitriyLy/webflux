package com.dmly.reactive.webflux.service;

import com.dmly.reactive.webflux.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UserServiceImpl implements ReactiveUserDetailsService {
    private final UserRepository repository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.findByUsername(username).cast(UserDetails.class);
    }
}
