package com.dmly.reactive.webflux.service;

import com.dmly.reactive.webflux.model.User;
import org.springframework.util.MultiValueMap;

public interface AuthService {
    boolean checkUserPassword(MultiValueMap<String, String> credentials, User userDetails);
}
