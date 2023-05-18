package com.example.mapper;

import com.example.entity.user.Account;
import com.example.entity.writing.Essay;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface usermapper {
    /**
     * 登录
     */
    @Select("select * from user where username = #{text} or email = #{text} or cellphone = #{text}")
    Account findAccountByNameOrEmail(String text);

    /**
     * 文章列表
     */
    @Select("SELECT aid,writer,Writingtime,label,photo,background,pageview,title,Articleoverview FROM article WHERE label = #{parameter}")
    List<Essay> QueryByTag(String parameter);

    /**
     * 文章正文
     */
    @Select("SELECT aid,text FROM article WHERE aid= #{aid}")
    Essay QuerythebodyoftheArticle(String aid);

    /**
     * 注册
     */
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    int createAccount(String username, String password);

    /**
     * 微信注册
     */
    @Insert("insert into user (openid) values (#{username})")
    int createwxAccount(String openid);
}