package org.example.snsproject.service;

import org.example.snsproject.entity.activity.Activity;
import org.example.snsproject.entity.activity.Activity_archives;
import org.example.snsproject.entity.blog.Article;

import java.util.List;

public interface ActivityService {
    //获取已结束的活动
    List<Activity> activitiesFinished();

    //获取未结束的活动
    List<Activity> activitiesUnFinished();

    //获取最热活动
    List<Activity> hotActivities();

    //获取最新活动
    List<Activity> newActivities();

    //获取特定活动内容
    Activity viewActivities(Integer id);

    //发布活动
    Activity publishActivities(Activity activity);

    //获取历史活动
    List<Activity_archives> listArchivesActivities();

    //获取指定活动
    Activity viewActivitiesById(int id);

    //获取当前用户参加过的活动
    List<Activity> getActivityUser();
}
