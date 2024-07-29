package org.example.snsproject.service.impl;

import org.example.snsproject.entity.UserProject;
import org.example.snsproject.mapper.ProjectMapper;
import org.example.snsproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper pm;

    @Override
    public List<UserProject> brief() {
        List<UserProject> ups = pm.brief();
        for (UserProject up : ups) {
            if (up.getInfo().length() > 32) {
                up.setInfo(up.getInfo().substring(0, 32));
            }
        }
        return ups;
    }

    @Override
    public UserProject detail(long pid) {
        return pm.detail(pid);
    }
}
