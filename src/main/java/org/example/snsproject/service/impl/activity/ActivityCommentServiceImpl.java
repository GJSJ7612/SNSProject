package org.example.snsproject.service.impl.activity;

import org.example.snsproject.entity.activity.Activity;
import org.example.snsproject.entity.activity.Activity_comment;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Comment;
import org.example.snsproject.mapper.activity.ActivityCommentMapper;
import org.example.snsproject.mapper.activity.ActivityMapper;
import org.example.snsproject.service.ActivityCommentService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ActivityCommentServiceImpl implements ActivityCommentService {
    @Autowired
    private ActivityCommentMapper activityCommentMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity_comment> getComments(int id) {
        List<Activity_comment> topComments = activityCommentMapper.getTopComments(id);
        for(Activity_comment comment : topComments){
            List<Activity_comment> childComments = activityCommentMapper.getComments(comment.getId());
            comment.setChildrens(childComments);
        }
        return topComments;
    }

    @Override
    public void createComment(Activity_comment activityComment) {
        activityComment.setCreateDate(LocalDateTime.now());
        Map<String, Object> claims = ThreadLocalUtil.get();
        activityComment.setAuthorId((int)claims.get("id"));
        List<Activity_comment> comments = activityCommentMapper.getTopComments(activityComment.getAuthorId());
        activityComment.setLevel(comments.size() + 1);
        if(activityComment.getParentId() == 0 && activityComment.getToUid() == 0){
            activityCommentMapper.createCommentNoPT(activityComment);
        }
        else activityCommentMapper.createComment(activityComment);
        Activity activity = activityMapper.viewActivities(activityComment.getArticleId());
        activityMapper.updateComment(activity.getId());
    }
}
