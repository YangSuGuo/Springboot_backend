package com.example.entity.user;

import lombok.Data;

/**
 * data 数据注解自动生成get、set
 * username         varchar(30)  null comment '用户名',
 * cellphone        varchar(11)  null comment '手机号',
 * email            varchar(30)  null comment '邮箱',
 * openid           varchar(100) null comment '微信openid',
 * u_random         varchar(20)  null comment '用户随机码',
 * password         varchar(255) null comment '密码',
 * nickname         varchar(20)  null comment '昵称',
 * photo            varchar(255) null comment '头像',
 * birthday         int          null comment '生日',
 * address          varchar(100) null comment '地址',
 * age              int          null comment '年龄',
 * introduction     varchar(300) null comment '个人简介',
 * pageview         int          null comment '浏览量',
 * Registrationtime int          null comment '注册时间'
 */
@Data
public class Account {
    int id;
    String username;
    String cellphone;
    String email;
    String openid;
    String u_random;
    String password;
    String nickname;
    String photo;
    int birthday;
    String address;
    int age;
    String introduction;
    int pageview;
    int Registrationtime;
}
