package com.example.demo_dzq.dto;

public class EditMemberRoleRequestDTO {
    private Integer societyId;
    private Integer userId;
    private String role; // 新角色，例如 "member" 或 "admin"

    // Getter 和 Setter
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
}
