package com.example.service.impl;

import com.example.entity.user.Account;
import com.example.mapper.usermapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Resource
    usermapper mapper;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     *登录
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        Account account = mapper.findAccountByNameOrEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名，密码不能为空");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }

    /**
     *注册
     */
    @Override
    public String validateAndRegister(String username, String password, String sessionId) {
        Account account = mapper.findAccountByNameOrEmail(username);
        if (account != null) return "此用户名已被注册，请更换用户名";

        password = encoder.encode(password);
        if (mapper.createAccount(username, password) > 0) {
            return null;
        } else {
            return "内部错误，请联系管理员";
        }
    }
}
