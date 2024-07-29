package org.example.snsproject.service.impl.referral;

import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Comment;
import org.example.snsproject.mapper.referral.CommentReferralMapper;
import org.example.snsproject.mapper.referral.ReferralMapper;
import org.example.snsproject.service.CommentsReferralService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CommentsReferralServiceImpl implements CommentsReferralService {
    @Autowired
    private CommentReferralMapper commentReferralMapper;
    @Autowired
    private ReferralMapper referralMapper;

    @Override
    public List<Comment> getComments(Integer id) {
        List<Comment> topComments = commentReferralMapper.getTopComments(id);
        for(Comment comment : topComments){
            List<Comment> childComments = commentReferralMapper.getComments(comment.getId());
            comment.setChildrens(childComments);
        }
        return topComments;
    }

    @Override
    public void createComment(Comment comment) {
        comment.setCreateDate(LocalDateTime.now());
        Map<String, Object> claims = ThreadLocalUtil.get();
        comment.setAuthorId((int)claims.get("id"));
        List<Comment> comments = commentReferralMapper.getTopComments(comment.getAuthorId());
        comment.setLevel(comments.size() + 1);
        if(comment.getParentId() == 0 && comment.getToUid() == 0){
            commentReferralMapper.createCommentNoPT(comment);
        }
        else commentReferralMapper.createComment(comment);
        Article article = referralMapper.articleDetail(comment.getArticleId());
        referralMapper.updateComment(article.getId(), article.getCommentCounts()+1);
    }
}
