package org.example.snsproject.service;

import org.example.snsproject.entity.activity.Activity_tag;
import org.example.snsproject.entity.blog.Tag;

import java.util.List;

public interface ActivityTagService {

    //获取全部标签
    List<Activity_tag> getActivityTags();

    //获取特定标签
    Activity_tag getActivityTagsById(int id);

    //获取全部标签细节
    List<Activity_tag> getActivityTagsDetail();

    //获取最热标签
    List<Activity_tag> getHotActivityTags();

    //获取特定标签细节
    Activity_tag getActivityTagsDetailbyId(int id);
}
