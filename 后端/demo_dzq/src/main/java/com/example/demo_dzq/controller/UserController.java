package com.example.demo_dzq.controller;

import com.example.demo_dzq.dto.UserRegisterDTO;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.dto.UserDTO;
import com.example.demo_dzq.service.UserService;
import com.example.demo_dzq.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<Response<UserRegisterDTO>> registerUser(
            @RequestParam("file") MultipartFile file,  // 上传头像文件
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone) {

        try {
            // 确保目录存在
            Path uploadDir = Paths.get("D:/icosImage");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // 创建目录
            }

            // 获取文件扩展名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : ".jpg";

            // 生成唯一的文件名，避免文件名冲突
            String newFileName = System.currentTimeMillis() + fileExtension;

            // 目标路径
            Path targetPath = uploadDir.resolve(newFileName);

            // 保存文件到磁盘
            file.transferTo(targetPath);

            // 构造头像的访问URL
            String imageUrl = "D:/icosImage/" + newFileName; // 根据你项目的实际访问路径调整

            System.out.println("Generated imageUrl (Local path): " + imageUrl);
            // 创建 User 对象
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setAvatarUrl(imageUrl); // 设置头像URL
            user.setBio("此人很懒，暂无介绍");
            user.setRole("enthusiast");
            // 调用服务层处理注册逻辑
            userMapper.insertUser(user);

            // 获取注册成功的用户信息
            User registeredUser = userService.findByUsername(user.getUsername());

            // 将 User 转换为 UserRegisterDTO
            UserRegisterDTO userDTO = new UserRegisterDTO(registeredUser.getUserId(), registeredUser.getUsername(), registeredUser.getEmail(), registeredUser.getAvatarUrl());

            // 返回成功响应
            Response<UserRegisterDTO> response = new Response<>(HttpStatus.CREATED.value(), "User registered successfully", userDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IOException e) {
            // 处理文件上传异常
            Response<UserRegisterDTO> errorResponse = new Response<>(HttpStatus.BAD_REQUEST.value(), "Error occurred while uploading avatar: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // 处理其他异常
            Response<UserRegisterDTO> errorResponse = new Response<>(HttpStatus.BAD_REQUEST.value(), "Error: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }



    // 用户登录接口
    @PostMapping("/login")
    public ResponseEntity<Response<UserDTO>> loginUser(@RequestBody User user) {
        try {
            // 根据用户名查找用户
            User existingUser = userService.findByUsername(user.getUsername());

            // 如果用户不存在
            if (existingUser == null) {
                Response<UserDTO> errorResponse = new Response<>(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password");
                return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            }

            // 验证密码 (直接比较明文密码)
            if (!user.getPassword().equals(existingUser.getPassword())) {
                Response<UserDTO> errorResponse = new Response<>(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password");
                return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            }

            // 密码匹配成功，返回用户信息（包括头像和角色）
            UserDTO userDTO = new UserDTO(
                    existingUser.getUserId(),
                    existingUser.getUsername(),
                    existingUser.getEmail(),
                    existingUser.getAvatarUrl(),  // 添加头像
                    existingUser.getRole()         // 添加角色
            );
            Response<UserDTO> response = new Response<>(HttpStatus.OK.value(), "Login successful", userDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // 返回系统错误信息
            Response<UserDTO> errorResponse = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 用户设置身份接口
    @PutMapping("/update")
    public Response<String> updateUser(@RequestParam Integer userId,
                                       @RequestParam String username,
                                       @RequestParam String role) {
        try {
            boolean updated = userService.updateUser(userId, username, role);
            if (updated) {
                return new Response<>(200, "User updated successfully", null);
            } else {
                return new Response<>(404, "User not found or update failed", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

    //用户修改信息接口
    @PutMapping("/updatePassword")
    public Response<String> updatePassword(@RequestParam Integer userId,
                                           @RequestParam String password,
                                           @RequestParam String phone,
                                           @RequestParam String newPassword) {
        try {
            boolean updated = userService.updatePassword(userId, password, phone, newPassword);
            if (updated) {
                return new Response<>(200, "Password updated successfully", null);
            } else {
                return new Response<>(400, "Incorrect password or phone number", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

}
