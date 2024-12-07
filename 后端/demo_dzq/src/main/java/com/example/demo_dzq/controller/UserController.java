package com.example.demo_dzq.controller;

import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.dto.UserDTO;
import com.example.demo_dzq.service.UserService;
import com.example.demo_dzq.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户注册接口
    @PostMapping("/register")
    public ResponseEntity<Response<UserDTO>> registerUser(@RequestBody User user) {
        try {
            // 调用服务层处理注册逻辑
            userService.registerUser(user);

            // 构建成功响应：仅返回非敏感信息
            User registeredUser = userService.findByUsername(user.getUsername()); // 获取注册成功的用户信息

            // 将 User 转换为 UserDTO
            UserDTO userDTO = new UserDTO(registeredUser.getUserId(), registeredUser.getUsername(), registeredUser.getEmail());

            Response<UserDTO> response = new Response<>(HttpStatus.CREATED.value(), "User registered successfully", userDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // 构建失败响应
            Response<UserDTO> errorResponse = new Response<>(HttpStatus.BAD_REQUEST.value(), "Error: " + e.getMessage());
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

            // 密码匹配成功，返回用户信息（不返回密码）
            UserDTO userDTO = new UserDTO(existingUser.getUserId(), existingUser.getUsername(), existingUser.getEmail());
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
