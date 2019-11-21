package com.scs.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private long id;
    private String title;
    private String intro;
    private String cover;
    private Integer diamond;
    private String nickname;
    private Integer comments;
    private Integer likes;
    private LocalDateTime publishtime;
    private int userid;
    private int typeid;
    private String content;
}
