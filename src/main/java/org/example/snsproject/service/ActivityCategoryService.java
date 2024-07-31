package org.example.snsproject.service;

import org.example.snsproject.entity.activity.Activity_category;

import java.util.List;

public interface ActivityCategoryService {

    //获取活动分类
    List<Activity_category> getActivityCategory();

    //获取具体活动分类
    Activity_category getActivityCategoryDetaile(Integer id);

    //跟据分类获取详细及其总数
    Activity_category getActivityCategoryActivities(Integer id);

    //获取所有分类
    List<Activity_category> getActivityCategoryAllActivities();
}
