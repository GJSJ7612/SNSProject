package org.example.snsproject.service.impl;

import org.example.snsproject.entity.User;
import org.example.snsproject.mapper.UserMapper;
import org.example.snsproject.service.UserService;
import org.example.snsproject.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByAccount(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void register(User user) {
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
        user.setAdminStatus(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.register(user);
    }

    @Override
    public void updateUserDetail(User user) {
        userMapper.updateUserDetail(user);
    }
}
