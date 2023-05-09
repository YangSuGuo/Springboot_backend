package com.example.mapper;

import com.example.entity.user.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface usermapper {
    /**
     * 查找登录
     */
    @Select("select * from user where username = #{text} or email = #{text} or cellphone = #{text}")
    Account findAccountByNameOrEmail(String text);

    /**
     * 插入注册
     */
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    int createAccount(String username, String password);

    /**
     * 插入微信注册
     */
    @Insert("insert into user (openid) values (#{username})")
    int createwxAccount(String openid);

}