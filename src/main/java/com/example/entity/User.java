package com.example.entity;

import lombok.Data;

/**
 * data 数据注解自动生成get、set
 */
@Data
public class User {
    int u_id;
    int regtime;
    String email;
    String username;
    String password;
    String face;
    String integral;
    String remainder;
}
