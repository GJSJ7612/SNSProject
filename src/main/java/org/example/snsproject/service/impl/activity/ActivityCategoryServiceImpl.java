package org.example.snsproject.service.impl.activity;

import org.example.snsproject.entity.activity.Activity_category;
import org.example.snsproject.mapper.activity.ActivityCategoryMapper;
import org.example.snsproject.service.ActivityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityCategoryServiceImpl implements ActivityCategoryService {
    @Autowired
    private ActivityCategoryMapper activityCategoryMapper;

    @Override
    public List<Activity_category> getActivityCategory() {
        return activityCategoryMapper.getActivityCategory();
    }

    @Override
    public Activity_category getActivityCategoryDetaile(Integer id) {
        return activityCategoryMapper.getActivityCategoryDetaile(id);
    }

    @Override
    public Activity_category getActivityCategoryActivities(Integer id) {
        return activityCategoryMapper.getActivityCategoryActivities(id);
    }

    @Override
    public List<Activity_category> getActivityCategoryAllActivities() {
        return activityCategoryMapper.getActivityCategoryAllActivities();
    }
}
