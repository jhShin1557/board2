package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardControllerTest {

    private final BoardDao boardDao;

    @Autowired
    public BoardControllerTest(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Test
    void write(){

        Board board = new Board();
        board.setTitle("타이틀");
        board.setContent("테스트");
        board.setWriterId("관리자");
        board.setWriter("관리자");

        for(int i = 0; i < 30; i++) {
            boardDao.write(board);
        }
    }

    @Test
    void clear() {
        boardDao.clear();
        boardDao.clearRecommend();
    }

}