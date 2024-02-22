package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.PageDto;
import com.example.demo.domain.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao implements AboutBoard{

    private final AboutBoard aboutBoard;

    @Autowired
    public BoardDao(AboutBoard aboutBoard) {
        this.aboutBoard = aboutBoard;
    }

    public int count() {
        return aboutBoard.count();
    }
    public void write(Board board) {
        aboutBoard.write(board);
    }

    public List<Board> list(PageDto pageDto) {
        return aboutBoard.list(pageDto);
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

    public void increaseRecommend(Integer number) {
        aboutBoard.increaseRecommend(number);
    }

    @Override
    public void recommend(Recommend recommend) {
        aboutBoard.recommend(recommend);
    }

    public void modify(Board board) {
        aboutBoard.modify(board);
    }

    public void clear() {
        aboutBoard.clear();
    }
}
