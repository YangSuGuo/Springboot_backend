package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import com.example.entity.writing.Essay;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AuthorizeService service;

    /**
     * 注册
     */
    @PostMapping("/register")
    public RestBean<String> registerUser(@Length(min = 2, max = 8) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         HttpSession session) {
        String s = service.validateAndRegister(username, password, session.getId());
        if (s == null)
            return RestBean.success("注册成功");
        else
            return RestBean.failure(400, s);
    }

    /**
     * 微信获取openid
     */
    @PostMapping("/wxuser/getOpenId")
    public RestBean<String> registerUser(@Length(min = 32, max = 32) @RequestParam("code") String code) {
        String openId = service.getUserOpenId(code);
        if (openId != null)
            return RestBean.success(openId);
        else
            return RestBean.failure(400);
    }

    /**
     * 获取文章概述
     */
/*    @PostMapping("/essay/list")
    public ResponseEntity<List<Essay>> list(@Length(min = 2, max = 20) @RequestParam("parameter") String parameter) {
        List<Essay> list = service.getlist(parameter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }*/

    @PostMapping("/essay/list")
    public String list (@Length(min = 2, max = 20) @RequestParam("parameter") String parameter) {
        List<Essay> list = service.getlist(parameter);
        return JSONObject.toJSONString(RestBean.success(list));
    }
/**
 * todo 获取文章正文 Post 文章id
 */

}