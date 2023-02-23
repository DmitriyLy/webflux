package com.dmly.reactive.webflux.service.impl;

import com.dmly.reactive.webflux.model.User;
import com.dmly.reactive.webflux.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean checkUserPassword(MultiValueMap<String, String> credentials, User userDetails) {
        return Objects.equals(credentials.getFirst("password"), userDetails.getPassword());
    }
}
