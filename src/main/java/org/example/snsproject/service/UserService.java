package org.example.snsproject.service;

import org.example.snsproject.entity.User;

public interface UserService {
    //通过用户名寻找用户
    User findUserByAccount(String account);

    //用户注册
    void register(User user);

    //更新用户信息
    void updateUserDetail(User user);
}
