package com.example.demo.repository;

import com.example.demo.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao implements AboutComment{

    private final AboutComment aboutComment;

    @Autowired
    public CommentDao(AboutComment aboutComment) {
        this.aboutComment = aboutComment;
    }
    @Override
    public void addComment(Comment comment) {
        aboutComment.addComment(comment);
    }

    @Override
    public List<Comment> getComment(Integer boardNo) {
        return aboutComment.getComment(boardNo);
    }

    @Override
    public void deleteComment(Long no) {
        aboutComment.deleteComment(no);
    }

    @Override
    public void clearComment() {
        aboutComment.clearComment();
    }
}
