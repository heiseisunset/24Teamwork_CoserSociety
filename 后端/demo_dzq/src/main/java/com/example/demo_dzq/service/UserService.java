package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.User;

public interface UserService {

    // 用户注册
    void registerUser(User user);

    // 根据用户名查找用户
    User findByUsername(String username);

    // 更新用户信息（用户名和角色）
    boolean updateUser(Integer userId, String username, String role);

    // 修改用户密码
    boolean updatePassword(Integer userId, String password, String phone, String newPassword);

    User getUserById(Integer userId);
}
