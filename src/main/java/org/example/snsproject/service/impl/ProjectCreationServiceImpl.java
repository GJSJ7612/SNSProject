package org.example.snsproject.service.impl;

import org.example.snsproject.entity.Project;
import org.example.snsproject.mapper.ProjectCreationMapper;
import org.example.snsproject.service.ProjectCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectCreationServiceImpl implements ProjectCreationService {

    @Autowired
    private ProjectCreationMapper pcm;

    @Override
    public void createProject(Project project) {
        project.setApplicants(null); // 无申请者
        project.setReceiver(-1); // 无接收项目者
        project.setCreateTime(LocalDateTime.now());
        pcm.createProject(project);
    }
}
