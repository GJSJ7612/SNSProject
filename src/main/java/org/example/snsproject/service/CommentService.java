package org.example.snsproject.service;

import org.example.snsproject.entity.blog.Comment;

import java.util.List;

public interface CommentService {

    //获取评论
    List<Comment> getComments(Integer id);

    //创建评论
    void createComment(Comment comment);
}
