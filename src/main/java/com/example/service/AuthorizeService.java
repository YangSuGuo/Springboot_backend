package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    String validateAndRegister(String username, String password, String sessionId);
}
