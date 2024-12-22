package com.example.demo_dzq.dto;

public class AddMemberRequestDTO {
    private String phoneNumber; // 用户手机号
    private String userName;    // 用户名
    private String role;        // 用户身份（"member" 或 "admin"）
    private Integer societyId;  // 社团ID

    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Integer societyId) {
        this.societyId = societyId;
    }
}