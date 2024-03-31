package com.example.demo.repository;

import com.example.demo.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AboutComment {
    void addComment(Comment comment);
    List<Comment> getComment(Integer boardNo);
    void deleteComment(Long no);

    void clearComment();
}
