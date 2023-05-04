package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface usermapper {
    @Select("select * from yuedu_user where username = #{text} or email = #{text}")
    User findAccountByNameOrEmail(String text);
}