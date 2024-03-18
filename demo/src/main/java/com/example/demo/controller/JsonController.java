package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Recommend;
import com.example.demo.repository.BoardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JsonController {

    private final BoardDao boardDao;

    @Autowired
    public JsonController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping("/recommend")
    public Board jsonMapping(@RequestBody Recommend recommend) {
      Integer boardNo = recommend.getBoardNo();
      boardDao.recommend(recommend);
      boardDao.increaseRecommend(boardNo);
      Board board = boardDao.findPage(boardNo);
      return board;
    }
}
