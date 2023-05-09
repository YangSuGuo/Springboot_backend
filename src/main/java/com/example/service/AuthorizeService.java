package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    /**
     * 注册
     */
    String validateAndRegister(String username, String password, String sessionId);
    /**
     * 微信登录，接收code
     */
    String getUserOpenId(String code);
}
