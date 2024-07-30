package org.example.snsproject.service.impl.activity;

import org.example.snsproject.entity.activity.Activity_category;
import org.example.snsproject.mapper.activity.ActivityCategoryMapper;
import org.example.snsproject.service.ActivityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityCategoryServiceImpl implements ActivityCategoryService {
    @Autowired
    private ActivityCategoryMapper activityCategoryMapper;

    @Override
    public Activity_category getActivityCategory() {

        return null;
    }
}
