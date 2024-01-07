package com.example.demo.domain;

import lombok.Data;

@Data
public class Like {
    private Long no;
    private Long board_no;
    private String writer_id;
    private boolean click;
}
