package com.example.demo_dzq.dto;

import com.example.demo_dzq.pojo.User;
import java.time.LocalDateTime;

public class SocietyMemberWithUserDTO {
    private Integer memberId;     // 成员ID
    private Integer societyId;    // 社团ID
    private Integer userId;       // 用户ID
    private String role;          // 角色
    private LocalDateTime joinedAt; // 加入时间
    private User user;            // 包含用户详细信息

    // Getter 和 Setter 方法

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
