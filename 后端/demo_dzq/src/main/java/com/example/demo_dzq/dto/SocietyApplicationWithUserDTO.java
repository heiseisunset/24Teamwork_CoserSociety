package com.example.demo_dzq.dto;

import com.example.demo_dzq.pojo.User;

import java.time.LocalDateTime;

public class SocietyApplicationWithUserDTO {
    private Integer applicationId;  // 申请ID
    private Integer societyId;      // 社团ID
    private Integer userId;         // 用户ID
    private String status;          // 状态
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
    private User user;              // 包含用户详细信息

    // Getter 和 Setter 方法

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Integer societyId) {
        this.societyId = societyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
