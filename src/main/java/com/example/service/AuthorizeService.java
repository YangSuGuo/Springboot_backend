package com.example.service;

import com.example.entity.writing.Essay;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AuthorizeService extends UserDetailsService {
    /**
     * 注册
     */
    String validateAndRegister(String username, String password, String sessionId);

    /**
     * 微信登录，接收code
     */
    String getUserOpenId(String code);


    /**
     * 获取文章列表
     * @param parameter 文章分类
     * @return  List<Essay> 卡片列表
     */
    List<Essay> getlist(String parameter);

    /**
     * 获取文章正文
     */
    Essay getarticle(int aid);
}

