package com.scs.blog.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String mobile;
    private String password;
    private String nickname;
    private String avatar;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String introduction;
    private String homepage;
    private Short follows;
    private Short fans;
    private Short article;
    private LocalDateTime createTime;
    private Short status;
}
