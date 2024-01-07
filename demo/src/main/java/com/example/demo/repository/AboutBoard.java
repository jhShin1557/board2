package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AboutBoard {
    void write(Board board);
    List<Board> list();
    Board findPage(Integer number);
    void delete(Integer number);
    void increaseViews(Integer number);
    void modify(Board board);

}
