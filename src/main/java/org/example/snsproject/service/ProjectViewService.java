package org.example.snsproject.service;

import org.example.snsproject.entity.UserProject;

import java.util.List;

public interface ProjectViewService {
    List<UserProject> brief();

    List<UserProject> applied();

    List<UserProject> created();

    List<UserProject> received();
}
