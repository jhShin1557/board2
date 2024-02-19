package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    private final AboutBoard aboutBoard;

    @Autowired
    public BoardDao(AboutBoard aboutBoard) {
        this.aboutBoard = aboutBoard;
    }
    public void write(Board board) {
        aboutBoard.write(board);
    }

    public List<Board> list() {
        return aboutBoard.list();
    }

    public Board findPage(Integer number) {
        return aboutBoard.findPage(number);
    }

    public void delete(Integer number) {
        aboutBoard.delete(number);
    }

    public void increaseViews(Integer number) {
        aboutBoard.increaseViews(number);
    }

    public void modify(Board board) {
        aboutBoard.modify(board);
    }

    public void clear() {
        aboutBoard.clear();
    }
}
