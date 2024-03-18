package com.example.demo.domain;

import lombok.Data;

@Data
public class Recommend {
    private Integer no;
    private Integer boardNo;
    private String recommendId;
    private String imgSrc;
    private boolean click;

}
