package org.example.snsproject.service;

import org.example.snsproject.entity.activity.Activity_comment;

import java.util.List;

public interface ActivityCommentService {

    //获取评论
    List<Activity_comment> getComments(int id);

    //发布评论
    void createComment(Activity_comment activityComment);
}
