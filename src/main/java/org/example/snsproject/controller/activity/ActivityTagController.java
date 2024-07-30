package org.example.snsproject.controller.activity;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.activity.Activity_tag;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.service.ActivityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityTagController {

    @Autowired
    private ActivityTagService activityTagService;

    @GetMapping("/activityTags")
    public Result<List<Activity_tag>> getActivityTags() {
        List<Activity_tag> data = activityTagService.getActivityTags();
        return Result.success(data);
    }

    @GetMapping("/activityTags/{id}")
    public Result<Activity_tag> getActivityTagsById(@PathVariable int id) {
        Activity_tag data = activityTagService.getActivityTagsById(id);
        return Result.success(data);
    }

    @GetMapping("/activityTags/detail")
    public Result<List<Activity_tag>> getActivityTagsDetail() {
        List<Activity_tag> data = activityTagService.getActivityTagsDetail();
        return Result.success(data);
    }

    @GetMapping("/activityTags/hot")
    public Result<List<Activity_tag>> getHotActivityTags() {
        List<Activity_tag> data = activityTagService.getHotActivityTags();
        return Result.success(data);
    }

    @GetMapping("/activityTags/detail/{id}")
    public Result<Activity_tag> getActivityTagsDetailbyId(@PathVariable int id) {
        Activity_tag data = activityTagService.getActivityTagsDetailbyId(id);
        return Result.success(data);
    }

}
