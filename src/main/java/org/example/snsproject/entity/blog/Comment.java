package org.example.snsproject.entity.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.snsproject.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
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
