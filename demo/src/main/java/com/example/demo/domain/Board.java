package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private Long no;
    private String title;
    private String content;
    private String writerId;
    private String writer;
    private Date regDate;
    private Integer views;
    private Integer recommend;
}
