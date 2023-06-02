package com.example.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.entity.user.Account;
import com.example.entity.writing.Essay;
import com.example.mapper.usermapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Resource
    usermapper mapper;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    /**
     * 小程序appId
     */
    @Value("${wx.appId}")
    private String appId;
    /**
     * 小程序密钥
     */
    @Value("${wx.secret}")
    private String secret;

    /**
     * 登录
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
     * 注册
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

    /**
     * 微信登录，获取openid
     */
    @Override
    public String getUserOpenId(String code) {
        String authUrl = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code";
        authUrl = authUrl + "&appid=" + appId + "&secret=" + secret + "&js_code=" + code;
        String result = HttpUtil.get(authUrl);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String openId = jsonObject.getStr("openid");

        if (openId != null) {
            return openId;
        } else {
            return "code错误";
        }
    }

    /**
     * 获取卡片列表
     */
    @Override
    public List<Essay> getlist(String parameter) {
        List<Essay> s = mapper.QueryByTag(parameter);
        return s;
    }

    @Override
    public List<Essay> getalllist(){
        List<Essay> s = mapper.AllQueryByTag();
        return s;
    }
    /**
     * 删除文章列表
     */
    @Override
    public List<Essay> deletelist(int aid){
        List<Essay> s = mapper.deletelist(aid);
        return s;
    }
    /**
     * 获取文章正文
     */
    @Override
    public Essay getarticle(int aid) {
        Essay s = mapper.QuerythebodyoftheArticle(aid);
        return s;
    }
}
