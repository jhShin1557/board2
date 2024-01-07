package com.example.demo.domain;

import lombok.Data;

@Data
public class User {
    private Long uniqueId;
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String gender;
}
