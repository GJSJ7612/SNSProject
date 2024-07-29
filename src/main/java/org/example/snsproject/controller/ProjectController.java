package org.example.snsproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.UserProject;
import org.example.snsproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProjectController {

    @Autowired
    private ProjectService ps;

    @GetMapping("/projects")
    public Result<List<UserProject>> brief(){
        return Result.success(ps.brief());
    }

    @GetMapping("/projects/{id}")
    public Result<UserProject> detail(@PathVariable String id){
        long pid = Long.parseLong(id);
        log.info("获取到任务ID：{}", pid);
        return Result.success(ps.detail(pid));
    }

}
