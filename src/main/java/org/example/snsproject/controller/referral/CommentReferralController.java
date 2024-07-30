package org.example.snsproject.controller.referral;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.blog.Comment;
import org.example.snsproject.service.CommentsReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentReferralController {

    @Autowired
    private CommentsReferralService commentsReferralService;

    //获取评论信息
    @GetMapping("/comments/recommend/{id}")
    private Result<List<Comment>> getComments(@PathVariable int id) {
        List<Comment> data = commentsReferralService.getComments(id);
        return Result.success(data);
    }

    //发布评论
    @PostMapping("/comments/recommend/create/change")
    private Result<List<Comment>> createComment(
            @RequestBody Comment comment) {
        commentsReferralService.createComment(comment);
        List<Comment> data = commentsReferralService.getComments(comment.getArticleId());
        return Result.success(data);
    }
}
