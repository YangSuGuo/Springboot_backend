package com.example.entity.writing;

import lombok.Data;

/**
 * aid     primary key,
 * writer          varchar(50)  null comment '写作人',
 * Writingtime     int          null comment '写作时间',
 * label           varchar(30)  null comment '标签',
 * photo           varchar(100) null comment '头像',
 * background      varchar(100) null comment '背景',
 * pageview        int          null comment '浏览量',
 * title           varchar(30)  null comment '文章标题',
 * Articleoverview varchar(100) null comment '文章概述',
 * text            mediumtext   null comment '文章正文'
 */
@Data
public class Essay {
    int aid;
    String writer;
    int Writingtime;
    String label;
    String photo;
    String background;
    int pageview;
    String title;
    String Articleoverview;
    String text;
}
