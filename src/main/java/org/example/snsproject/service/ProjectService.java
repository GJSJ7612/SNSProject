package org.example.snsproject.service;

import org.example.snsproject.entity.Application;
import org.example.snsproject.entity.UserProjectDetail;

public interface ProjectService {
    UserProjectDetail detail(long pid);

    long apply(long pid, Application application);

    void choose(long pid, int uid);
}
