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

    @Override
    public int count() {
        return aboutBoard.count();
    }

    @Override
    public void write(Board board) {
        aboutBoard.write(board);
    }

    @Override
    public List<Board> list(PageDto pageDto) {
        return aboutBoard.list(pageDto);
    }

    @Override
    public Board findPage(Integer number) {
        return aboutBoard.findPage(number);
    }

    @Override
    public void delete(Integer number) {
        aboutBoard.delete(number);
    }

    @Override
    public void increaseViews(Integer number) {
        aboutBoard.increaseViews(number);
    }

    @Override
    public void increaseRecommend(Integer number) {
        aboutBoard.increaseRecommend(number);
    }

    @Override
    public void decreaseRecommend(Integer number) {
        aboutBoard.decreaseRecommend(number);
    }

    @Override
    public void recommend(Recommend recommend) {
        aboutBoard.recommend(recommend);
    }

    @Override
    public void cancelRecommend(Recommend recommend) {
        aboutBoard.cancelRecommend(recommend);
    }

    @Override
    public void modify(Board board) {
        aboutBoard.modify(board);
    }

    @Override
    public void clear() {
        aboutBoard.clear();
    }

    @Override
    public void clearRecommend() {
        aboutBoard.clearRecommend();
    }

    @Override
    public Recommend findRecommendId(Recommend recommend) {
        return aboutBoard.findRecommendId(recommend);
    }

}
