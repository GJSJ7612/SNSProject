package org.example.snsproject.controller.project;

import org.example.snsproject.entity.project.Application;
import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.project.UserProjectDetail;
import org.example.snsproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService ps;

    @GetMapping("/projects/{pid}")
    public Result<UserProjectDetail> detail(@PathVariable long pid){
        UserProjectDetail upd = ps.detail(pid);
        return Result.success(upd);
    }

    @PostMapping("/projects/{pid}")
    public Result<Long> apply(@PathVariable long pid, Application application) {
        long id = ps.apply(pid, application);
        // 获取申请操作的ID
        return Result.success(id);
    }

    @PostMapping("/projects/{pid}")
    public Result<Object> choose(@PathVariable long pid, int uid) {
        ps.choose(pid, uid);
        return Result.success();
    }
}
