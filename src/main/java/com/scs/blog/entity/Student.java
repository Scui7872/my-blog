package com.scs.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private Integer id;
    private String username;
    private String avatar;
    private String description;
    private LocalDateTime createTime;
}
