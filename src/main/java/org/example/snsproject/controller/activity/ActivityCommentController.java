package org.example.snsproject.controller.activity;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.activity.Activity_comment;
import org.example.snsproject.entity.blog.Comment;
import org.example.snsproject.service.ActivityCommentService;
import org.example.snsproject.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityCommentController {
    @Autowired
    private ActivityCommentService activityCommentService;


    @GetMapping("/comments/activity/{id}")
    public Result<List<Activity_comment>> getActivityComment(@PathVariable int id) {
        List<Activity_comment> data = activityCommentService.getComments(id);
        return Result.success(data);
    }

    @PostMapping("/activity/comments/create/change")
    public Result<List<Activity_comment>> createActivityComment(@RequestBody Activity_comment activityComment) {
        activityCommentService.createComment(activityComment);
        List<Activity_comment> data = activityCommentService.getComments(activityComment.getArticleId());
        return Result.success(data);
    }
}
