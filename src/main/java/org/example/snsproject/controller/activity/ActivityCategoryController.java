package org.example.snsproject.controller.activity;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.activity.Activity_category;
import org.example.snsproject.service.ActivityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityCategoryController {

    @Autowired
    private ActivityCategoryService activityCategoryService;

    @GetMapping("/activityCategorys")
    public Result<List<Activity_category>> getActivityCategory() {
        List<Activity_category> data = activityCategoryService.getActivityCategory();
        return Result.success(data);
    }

    @GetMapping("/activityCategorys/{id}")
    public Result<Activity_category> getActivityCategoryDetaile(@PathVariable Integer id) {
        Activity_category data = activityCategoryService.getActivityCategoryDetaile(id);
        return Result.success(data);
    }

    @GetMapping("/activityCategorys/detail/{id}")
    public Result<Activity_category> getActivityCategoryActivities(@PathVariable Integer id) {
        Activity_category data = activityCategoryService.getActivityCategoryActivities(id);
        return Result.success(data);
    }

    @GetMapping("/activityCategorys/detail")
    public Result<List<Activity_category>> getActivityCategoryAllActivities() {
        List<Activity_category> data = activityCategoryService.getActivityCategoryAllActivities();
        return Result.success(data);
    }
}
