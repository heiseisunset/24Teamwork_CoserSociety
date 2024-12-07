package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // 引入 BCryptPasswordEncoder
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);  // 查询数据库返回 User 对象
    }

    @Override
    public void registerUser(User user) {
        // 校验用户名是否已存在
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("Username is already taken");
        }

        // 校验邮箱是否已存在
        User existingEmail = userMapper.findByEmail(user.getEmail());
        if (existingEmail != null) {
            throw new UserAlreadyExistsException("Email is already registered");
        }

        // 可选：校验手机号是否已存在
        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            User existingPhone = userMapper.findByPhone(user.getPhone());
            if (existingPhone != null) {
                throw new UserAlreadyExistsException("Phone number is already registered");
            }
        }


        // 设置默认值：头像和简介
        user.setAvatarUrl(""); // 默认头像
        user.setBio(""); // 默认简介
        user.setRole("enthusiast"); // 默认角色为爱好者
        // 使用 Timestamp
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now())); // 将 LocalDateTime 转换为 Timestamp

        // 将新用户插入数据库
        userMapper.insertUser(user);
    }


    @Override
    public boolean updateUser(Integer userId, String username, String role) {
        // 检查用户是否存在
        User existingUser = userMapper.findById(userId);

        // 检查用户名是否已被其他用户占用
        User userWithSameUsername = userMapper.findByUsername(username);
        if (userWithSameUsername != null && userWithSameUsername.getUserId() != userId) {
            throw new IllegalArgumentException("Username is already taken by another user.");
        }


        // 验证角色是否合法
        if (!isValidRole(role)) {
            throw new IllegalArgumentException("Invalid role specified.");
        }

        // 更新用户信息
        existingUser.setUsername(username);
        existingUser.setRole(role);
        userMapper.updateUser(existingUser);

        return true;
    }

    @Override
    public boolean updatePassword(Integer userId, String password, String phone, String newPassword) {
        // 根据 userId 查找用户
        User user = userMapper.findById(userId);
        if (user == null) {
            return false; // 用户不存在
        }

        // 验证传入的密码和电话是否与数据库中的记录匹配
        if (user.getPassword().equals(password) && user.getPhone().equals(phone)) {
            // 如果匹配，则更新密码
            user.setPassword(newPassword);
            userMapper.updatePassword(user);
            return true;
        } else {
            return false; // 密码或电话不匹配
        }
    }

    // 验证角色是否合法
    private boolean isValidRole(String role) {
        return role.equals("enthusiast") || role.equals("coser") ||
                role.equals("post_production") || role.equals("photographer") ||
                role.equals("makeup_artist") || role.equals("organizer");
    }
    // 自定义异常：用户已存在
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }
}
