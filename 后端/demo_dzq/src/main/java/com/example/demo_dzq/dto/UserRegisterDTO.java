package com.example.demo_dzq.dto;

public class UserRegisterDTO {
    private Integer userId;
    private String username;
    private String email;
    private String avatarUrl; // 新增头像字段

    // 构造函数
    public UserRegisterDTO(Integer userId, String username, String email, String avatarUrl) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    // Getter 和 Setter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
