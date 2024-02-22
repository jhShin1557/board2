package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Recommend;
import com.example.demo.repository.BoardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JsonController {

    private final BoardDao boardDao;

    @Autowired
    public JsonController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping("/jsonTest")
    public void jsonMapping(@RequestBody Recommend recommend) {
      log.info("recommend={}", recommend);
      Integer number = recommend.getBoardNo();
      boardDao.recommend(recommend);
      boardDao.increaseRecommend(number);
    }
}
