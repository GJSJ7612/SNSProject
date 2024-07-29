package org.example.snsproject.controller.blog;

import org.example.snsproject.entity.blog.Comment;
import org.example.snsproject.entity.Result;
import org.example.snsproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/article/{id}")
    private Result<List<Comment>> getComments(@PathVariable int id) {
        List<Comment> data = commentService.getComments(id);
        return Result.success(data);
    }

    @PostMapping("/comments/create/change")
    private Result<List<Comment>> createComment(
            @RequestBody Comment comment) {
        commentService.createComment(comment);
        List<Comment> data = commentService.getComments(comment.getArticleId());
        return Result.success(data);
    }
}
