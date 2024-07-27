package org.example.snsproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.snsproject.entity.Project;
import org.example.snsproject.entity.Result;
import org.example.snsproject.service.ProjectCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProjectCreationController {

    @Autowired
    private ProjectCreationService pcs;

    /**
     * 创建发包项目
     * @param project 项目
     * @return 项目ID
     */
    @PostMapping("/project/create")
    public Result<Long> createProject(@RequestBody Project project){
        log.info("新建项目：{}",project);
        pcs.createProject(project);
        return Result.success();
    }

}
