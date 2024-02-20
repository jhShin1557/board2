package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.Like;
import com.example.demo.domain.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AboutBoard {
    int count();
    void write(Board board);
    List<Board> list(PageDto pageDto);
    Board findPage(Integer number);
    void delete(Integer number);
    void increaseViews(Integer number);
    void modify(Board board);
    void clear();
}
