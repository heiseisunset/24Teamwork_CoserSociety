package com.example.demo_dzq.dto;

public class UserDTO {

    private int userId;
    private String username;
    private String email;

    // 构造函数
    public UserDTO(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // getter 和 setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
}
