package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.PageDto;
import com.example.demo.domain.Recommend;
import com.example.demo.domain.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AboutBoard {
    int count(Search search);
    void write(Board board);
    List<Board> list(PageDto pageDto);
    Board findPage(Integer number);
    void delete(Integer number);
    void increaseViews(Integer number);
    void increaseRecommend(Integer number);
    void decreaseRecommend(Integer number);
    void recommend(Recommend recommend);
    void cancelRecommend(Recommend recommend);
    void modify(Board board);
    void clear();
    void clearRecommend();
    Recommend findRecommendId(Recommend recommend);

}
