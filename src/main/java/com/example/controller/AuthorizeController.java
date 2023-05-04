package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AuthorizeService service;
    @PostMapping("/register")
    public RestBean<String> registerUser(@Length(min = 2, max = 8) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         HttpSession session) {
        String s = service.validateAndRegister(username, password, session.getId());
        if(s == null)
            return RestBean.failure(200, "注册成功");
        else
            return RestBean.failure(400, s);
    }
}
