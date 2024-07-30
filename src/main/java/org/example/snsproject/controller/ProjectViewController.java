package org.example.snsproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.UserProject;
import org.example.snsproject.service.ProjectViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProjectViewController {

    @Autowired
    private ProjectViewService pvs;

    @GetMapping("/projects")
    public Result<List<UserProject>> brief(){
        return Result.success(pvs.brief());
    }

    @GetMapping("/projects/applied")
    public Result<List<UserProject>> applied(){
        return Result.success(pvs.applied());
    }

    @GetMapping("/projects/created")
    public Result<List<UserProject>> created(){
        return Result.success(pvs.created());
    }

    @GetMapping("/projects/received")
    public Result<List<UserProject>> received(){
        return Result.success(pvs.received());
    }

}
