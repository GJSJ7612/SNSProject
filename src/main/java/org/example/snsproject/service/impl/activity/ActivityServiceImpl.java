package org.example.snsproject.service.impl.activity;

import org.example.snsproject.entity.activity.Activity;
import org.example.snsproject.entity.activity.Activity_archives;
import org.example.snsproject.entity.activity.Activity_tag;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.activity.ActivityMapper;
import org.example.snsproject.service.ActivityService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> activitiesFinished(Integer tagId, Integer categoryId) {
        return activityMapper.activitiesFinished(tagId, categoryId, LocalDateTime.now());
    }

    @Override
    public List<Activity> activitiesUnFinished(Integer tagId, Integer categoryId) {
        return activityMapper.activitiesUnFinished(tagId, categoryId, LocalDateTime.now());
    }

    @Override
    public List<Activity> hotActivities() {
        return activityMapper.hotActivities();
    }

    @Override
    public List<Activity> newActivities() {
        return activityMapper.newActivities();
    }

    @Override
    public Activity viewActivities(Integer id) {
        activityMapper.updateViewActivities(id);
        return activityMapper.viewActivities(id);
    }

    @Override
    public Activity publishActivities(Activity activity) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        activity.setAuthorId(id);
        activity.setCreateDate(LocalDateTime.now());
        activityMapper.insertBody(activity.getBody());
        activity.setBodyId(activity.getBody().getId());
        activity.setCategoryId(activity.getCategory().getId());
        activityMapper.insertArticle(activity);
        for(Activity_tag tag : activity.getTags()) {
            activityMapper.insertArticleTag(activity.getId(), tag.getId());
        }
        activity.setId(activity.getId());
        return activity;
    }

    @Override
    public List<Activity_archives> listArchivesActivities() {
        return activityMapper.listArchivesActivities();
    }

    @Override
    public Activity viewActivitiesById(int id) {
        return activityMapper.viewActivities(id);
    }

    @Override
    public List<Activity> getActivityUser() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        return activityMapper.getActivityUser(id);
    }


}
