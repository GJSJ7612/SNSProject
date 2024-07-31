package org.example.snsproject.entity.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.snsproject.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Activity_comment {
    private int id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    private int activityId;
    private int authorId;
    private int parentId;
    private int toUid;
    private User toUser;
    private int level;
    private List<Activity_comment> childrens;
    private User author;
}
