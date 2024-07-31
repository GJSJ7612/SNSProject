package org.example.snsproject.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.snsproject.entity.project.Application;
import org.example.snsproject.entity.project.UserProjectDetail;
import org.example.snsproject.mapper.project.ProjectMapper;
import org.example.snsproject.service.ProjectService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper pm;

    @Override
    public UserProjectDetail detail(long pid) {
        UserProjectDetail upd = pm.detail(pid);
        List<Application> applications = pm.applicants(pid);
        Map<String, Object> claims = ThreadLocalUtil.get();
        int uid = (Integer) claims.get("id");
        if (uid == upd.getUid()) {
            // 发布者有权查看申请的详情
            upd.setApplied(0);
            upd.setApplications(applications);
            // receiver保留
        }
        else {
            boolean isApplicant = false; // 当前用户是否申请过
            for (Application a : applications) {
                if (a.getUid() == uid) {
                    isApplicant = true;
                    break;
                }
            }
            if (isApplicant) {
                if (upd.getReceiver() == -1) {
                    // 已申请，未出结果
                    upd.setApplied(2);
                    // receiver保留
                }
                else if (upd.getReceiver() == uid) {
                    // 申请成功
                    upd.setApplied(3);
                    // receiver保留
                }
                else {
                    // 申请失败
                    upd.setApplied(4);
                    upd.setReceiver(-1);
                }
            }
            else {
                if (upd.getReceiver() == -1) {
                    // 当前可申请
                    upd.setApplied(1);
                    // receiver保留
                }
                else {
                    // “与我无关”
                    upd.setApplied(-1);
                    upd.setReceiver(-1);
                }
            }
        }
        return upd;
    }

    @Override
    public long apply(long pid, Application application) {
        assert pid == application.getPid();
        Map<String, Object> claims = ThreadLocalUtil.get();
        int uid = (Integer) claims.get("id");
        application.setUid(uid);
        // 前端无需提供申请者的ID
        application.setPid(pid);
        // 使用路径中的pid，无需前端在application中提供pid
        int lines = pm.apply(application);
        log.info("数据库application表中{}行受到影响", lines);
        return application.getId();
    }

    @Override
    public void choose(long pid, int uid) {
        pm.choose(pid, uid);
    }
}
