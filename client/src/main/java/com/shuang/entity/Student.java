package com.shuang.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Long id;
    private String name;
    private Double score;
    private Date birthday;
}