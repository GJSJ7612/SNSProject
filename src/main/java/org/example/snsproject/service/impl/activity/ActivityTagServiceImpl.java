package org.example.snsproject.service.impl.activity;

import org.example.snsproject.entity.activity.Activity_tag;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.activity.ActivityTagMapper;
import org.example.snsproject.service.ActivityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTagServiceImpl implements ActivityTagService {

    @Autowired
    private ActivityTagMapper activityTagMapper;

    @Override
    public List<Activity_tag> getActivityTags() {
        return activityTagMapper.getActivityTags();
    }

    @Override
    public Activity_tag getActivityTagsById(int id) {
        return activityTagMapper.getActivityTagsById(id);
    }

    @Override
    public List<Activity_tag> getActivityTagsDetail() {
        return activityTagMapper.etActivityTagsDetail();
    }

    @Override
    public List<Activity_tag> getHotActivityTags() {
        return activityTagMapper.getHotActivityTags();
    }

    @Override
    public Activity_tag getActivityTagsDetailbyId(int id) {
        return activityTagMapper.getActivityTagsDetailbyId(id);
    }
}
