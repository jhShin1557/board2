package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private Long no;
    private String title;
    private String content;
    private String writer_id;
    private String writer;
    private Date reg_date;
    private Integer views;
    private Integer recommend;
}
