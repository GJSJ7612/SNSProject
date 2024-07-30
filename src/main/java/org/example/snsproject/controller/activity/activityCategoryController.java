package org.example.snsproject.controller.activity;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.activity.Activity_category;
import org.example.snsproject.service.ActivityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class activityCategoryController {

    @Autowired
    private ActivityCategoryService activityCategoryService;

    @GetMapping("/activityCategorys")
    public Result<Activity_category> getActivityCategory() {
        Activity_category data = activityCategoryService.getActivityCategory();
        return Result.success(data);
    }
}
