package com.example.service;

import com.example.entity.user.Account;
import com.example.entity.writing.Essay;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AuthorizeService extends UserDetailsService {
    /**
     * 注册
     */
    String validateAndRegister(String username, String password, String sessionId);

    /**
     * 用户信息
     */
    Account UserInformation(String user);

    /**
     * 微信登录，接收code
     */
    String getUserOpenId(String code);


    /**
     * 获取文章列表
     *
     * @param parameter 文章分类
     * @return List<Essay> 卡片列表
     */
    List<Essay> getlist(String parameter);

    List<Essay> getalllist();

    /**
     * 浏览量++
     */
    Essay updatepageviewgaga(int aid);

    /**
     * 删除文章列表
     */
    List<Essay> deletelist(int aid);

    /**
     * 获取文章正文
     */
    Essay getarticle(int aid);
    /**
     * 上传文章
     */
    Essay Insertarecord(String writer, String Writingtime, String label, String background, String title, String Articleoverview, String text);
}

