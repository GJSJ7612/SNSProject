package org.example.snsproject.service.impl.blog;

import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Comment;
import org.example.snsproject.mapper.blog.ArticleMapper;
import org.example.snsproject.mapper.blog.CommentMapper;
import org.example.snsproject.service.CommentService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Comment> getComments(Integer id) {
        List<Comment> topComments = commentMapper.getTopComments(id);
        for(Comment comment : topComments){
            List<Comment> childComments = commentMapper.getComments(comment.getId());
            comment.setChildrens(childComments);
        }
        return topComments;
    }

    @Override
    public void createComment(Comment comment) {
        comment.setCreateDate(LocalDateTime.now());
        Map<String, Object> claims = ThreadLocalUtil.get();
        comment.setAuthorId((int)claims.get("id"));
        List<Comment> comments = commentMapper.getTopComments(comment.getAuthorId());
        comment.setLevel(comments.size() + 1);
        if(comment.getParentId() == 0 && comment.getToUid() == 0){
            commentMapper.createCommentNoPT(comment);
        }
        else commentMapper.createComment(comment);
        Article article = articleMapper.articleDetail(comment.getArticleId());
        articleMapper.updateComment(article.getId(), article.getCommentCounts()+1);
    }
}
