package org.example.snsproject.controller.activity;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.User;
import org.example.snsproject.entity.activity.Activity;
import org.example.snsproject.entity.activity.Activity_archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/activitiesFinished")
    public Result<List<Activity>> activitiesFinished(Integer tagId, Integer categoryId) {
        if(tagId == null){
            tagId = -1;
        }
        if(categoryId == null){
            categoryId = -1;
        }
        List<Activity> data = activityService.activitiesFinished(tagId, categoryId);
        return Result.success(data);
    }

    @GetMapping("/activitiesUnFinished")
    public Result<List<Activity>> activitiesUnFinished(Integer tagId, Integer categoryId) {
        if(tagId == null){
            tagId = -1;
        }
        if(categoryId == null){
            categoryId = -1;
        }
        List<Activity> data = activityService.activitiesUnFinished(tagId, categoryId);
        return Result.success(data);
    }

    @GetMapping("/activities/hot")
    public Result<List<Activity>> hotActivities() {
        List<Activity> data = activityService.hotActivities();
        return Result.success(data);
    }

    @GetMapping("/activities/new")
    public Result<List<Activity>> newActivities() {
        List<Activity> data = activityService.newActivities();
        return Result.success(data);
    }

    @GetMapping("/activities/view/{id}")
    public Result<Activity> viewActivities(@PathVariable int id) {
        Activity data = activityService.viewActivities(id);
        return Result.success(data);
    }

    @PostMapping("/activities/publish")
    public  Result<Activity> publishActivities(@RequestBody Activity activity) {
        Activity data = activityService.publishActivities(activity);
        return Result.success(data);
    }

    @GetMapping("/activities/listArchives")
    public Result<List<Activity_archives>> listArchivesActivities() {
        List<Activity_archives> data = activityService.listArchivesActivities();
        return Result.success(data);
    }

    @GetMapping("/activities/{id}")
    public Result<Activity> viewActivitiesById(@PathVariable int id) {
        Activity data = activityService.viewActivitiesById(id);
        return Result.success(data);
    }

    @GetMapping("/activities/user")
    public Result<List<Activity>> getActivityUser() {
        List<Activity> data = activityService.getActivityUser();
        return Result.success(data);
    }
}
