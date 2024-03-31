package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardDao;
import com.example.demo.repository.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private CommentDao commentDao;

    @Test
    void write(){

        Board board = new Board();
        board.setWriterId("관리자");
        board.setWriter("관리자");

        for(int i = 1; i <= 2000; i++) {
            board.setTitle("게시물 " + i);
            board.setContent("테스트 " + i);
            boardDao.write(board);
        }
    }

    @Test
    void clear() {
        boardDao.clear();
        boardDao.clearRecommend();
        commentDao.clearComment();
    }

}