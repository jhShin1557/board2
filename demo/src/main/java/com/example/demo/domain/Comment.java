package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Comment {

    private Long no;
    private Integer boardNo;
    private String userId;
    private String content;
    private Date regDate;
}
