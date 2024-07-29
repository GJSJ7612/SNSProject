package org.example.snsproject.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.snsproject.entity.Project;
import org.example.snsproject.mapper.ProjectCreationMapper;
import org.example.snsproject.service.ProjectCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ProjectCreationServiceImpl implements ProjectCreationService {

    @Autowired
    private ProjectCreationMapper pcm;

    @Override
    public long createProject(Project project) {
        project.setApplicants(null); // 无申请者
        project.setReceiver(-1); // 无接收项目者
        project.setCreateTime(LocalDateTime.now());
        int lines = pcm.createProject(project);
        log.info("数据库project表中{}行受到影响", lines);
        return project.getPid();
    }
}
