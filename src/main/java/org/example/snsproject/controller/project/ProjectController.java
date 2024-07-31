package org.example.snsproject.controller.project;

import org.example.snsproject.entity.project.Application;
import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.project.UserProjectDetail;
import org.example.snsproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService ps;

    @GetMapping("/projects/{pid}")
    public Result<UserProjectDetail> detail(@PathVariable long pid){
        UserProjectDetail upd = ps.detail(pid);
        return Result.success(upd);
    }

    @PostMapping("/projects/{pid}/apply")
    public Result<Long> apply(@PathVariable long pid, @RequestBody Application application) {
        long id = ps.apply(pid, application);
        // 获取申请操作的ID
        return Result.success(id);
    }

    @PostMapping("/projects/{pid}/choose/{uid}")
    public Result<Object> choose(@PathVariable long pid, @PathVariable int uid) {
        ps.choose(pid, uid);
        return Result.success();
    }
}
