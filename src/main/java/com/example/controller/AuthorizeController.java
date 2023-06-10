package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import com.example.entity.user.Account;
import com.example.entity.writing.Essay;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 用户信息
     */
    @PostMapping("/UserInformation")
    public Account UserInformation(@Length(min = 2, max = 20) @RequestParam("user") String user) {
        Account s = service.UserInformation(user);
        return s;
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
    @PostMapping("/essay/list")
    public ResponseEntity<List<Essay>> list(@Length(min = 2, max = 20) @RequestParam("parameter") String parameter) {
        List<Essay> list = service.getlist(parameter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/essay/alllist")
    public ResponseEntity<List<Essay>> list() {
        List<Essay> list = service.getalllist();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 删除文章
     */
    @PostMapping("/essay/deletelist")
    public RestBean<String> list(@RequestParam("aid") int aid) {
        service.deletelist(aid);
        return null;
    }

    /**
     * 获取文章正文 Post 文章id
     *  todo 调用一次使文章阅读量++方法 ok
     */
    @PostMapping("/essay/article")
    public String article(@RequestParam("aid") int aid) {
        Essay s = service.getarticle(aid);
        Essay pageview = service.updatepageviewgaga(aid);
        if (pageview == null)
            return JSONObject.toJSONString(s);
        else
            return JSONObject.toJSONString(s);
    }
    // todo 上传文章描述接口
    // todo 上传文章正文接口
    // todo 按用户名搜索文章列表接口 ok
    // todo 用户详细信息接口

}