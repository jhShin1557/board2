package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AboutUser {
    void join(User user); //회원가입 메서드

    User isExist(User user); //회원가입 중복 아이디 검사

    User isCorrect(User user); //로그인 정보 일치 검증 메서드

    User findById(String id); //아이디로 정보 찾기
}
