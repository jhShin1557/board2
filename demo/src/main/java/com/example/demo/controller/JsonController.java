package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Recommend;
import com.example.demo.repository.BoardDao;
import com.example.demo.repository.CommentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class JsonController {

    private final BoardDao boardDao;
    private final CommentDao commentDao;
    @Autowired
    public JsonController(BoardDao boardDao, CommentDao commentDao) {
        this.boardDao = boardDao;
        this.commentDao = commentDao;
    }
    @RequestMapping("/recommend")
    public Board jsonMapping(@RequestBody Recommend recommend) {
        Integer boardNo = recommend.getBoardNo();
        Recommend searchRecommend = boardDao.findRecommendId(recommend);

        if (searchRecommend == null) {
            boardDao.recommend(recommend);
            boardDao.increaseRecommend(boardNo);

        } else {
            boardDao.cancelRecommend(recommend);
            boardDao.decreaseRecommend(boardNo);
        }

        Board board = boardDao.findPage(boardNo);

        return board;
    }
}
