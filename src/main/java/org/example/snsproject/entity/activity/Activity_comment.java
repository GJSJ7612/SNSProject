package org.example.snsproject.entity.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.snsproject.entity.User;
import org.example.snsproject.entity.blog.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class Activity_comment {
    private int id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    private int articleId;
    private int authorId;
    private int parentId;
    private int toUid;
    private User toUser;
    private int level;
    private List<Comment> childrens;
    private User author;
}
