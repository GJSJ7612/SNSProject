package org.example.snsproject.service.impl;

import org.example.snsproject.entity.project.UserProject;
import org.example.snsproject.mapper.project.ProjectViewMapper;
import org.example.snsproject.service.ProjectViewService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectViewServiceImpl implements ProjectViewService {

    @Autowired
    private ProjectViewMapper pvm;

    @Override
    public List<UserProject> brief() {
        List<UserProject> ups = pvm.brief();
        return shorten(ups);
    }

    @Override
    public List<UserProject> applied() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        List<UserProject> ups = pvm.applied((Integer) claims.get("id"));
        return shorten(ups);
    }

    @Override
    public List<UserProject> created() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        List<UserProject> ups = pvm.created((Integer) claims.get("id"));
        return shorten(ups);
    }

    @Override
    public List<UserProject> received() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        List<UserProject> ups = pvm.received((Integer) claims.get("id"));
        return shorten(ups);
    }

    private List<UserProject> shorten(List<UserProject> ups){
        for (UserProject up : ups) {
            if (up.getInfo().length() > 32) {
                up.setInfo(up.getInfo().substring(0, 32));
            }
        }
        return ups;
    }
}
