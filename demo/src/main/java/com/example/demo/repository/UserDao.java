package com.example.demo.repository;

import com.example.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Slf4j
@Repository
public class UserDao implements AboutUser{

    private final AboutUser aboutUser;

    @Autowired
    public UserDao(AboutUser aboutUser) {
        this.aboutUser = aboutUser;
    }

    public void join(User user) {
        aboutUser.join(user);
        log.info("오버라이딩 메서드");
    }

    @Override
    public User isExist(User user) {
        return aboutUser.isExist(user);
    }

    @Override
    public User isCorrect(User user) {
        return aboutUser.isCorrect(user);
    }

    @Override
    public User findById(String id) {
        return aboutUser.findById(id);
    }
}
