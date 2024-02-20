package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class Page2ControllerTest {

    private final BoardDao boardDao;

    @Autowired
    public Page2ControllerTest(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Test
    void write(){

        Board board = new Board();
        board.setTitle("테스트");
        board.setContent("테스트");
        board.setWriter_id("관리자");
        board.setWriter("관리자");

        for(int i = 0; i < 10000; i++) {
            boardDao.write(board);
        }
    }

    @Test
    void clear() {
        boardDao.clear();
    }

}