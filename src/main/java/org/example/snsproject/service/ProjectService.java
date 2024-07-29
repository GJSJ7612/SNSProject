package org.example.snsproject.service;

import org.example.snsproject.entity.UserProject;

import java.util.List;

public interface ProjectService {
    List<UserProject> brief();

    UserProject detail(long pid);
}
