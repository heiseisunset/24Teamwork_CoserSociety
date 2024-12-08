package com.example.demo_dzq.dto;
import com.example.demo_dzq.pojo.Society;
import com.example.demo_dzq.pojo.User;
import java.util.List;

public class SocietyDetailResponseDTO {

    private Society society;  // 社团信息
    private List<SocietyMemberDetail> members;  // 成员信息

    public SocietyDetailResponseDTO(Society society, List<SocietyMemberDetail> members) {
        this.society = society;
        this.members = members;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public List<SocietyMemberDetail> getMembers() {
        return members;
    }

    public void setMembers(List<SocietyMemberDetail> members) {
        this.members = members;
    }

    // 内部类用于封装成员的详细信息
    public static class SocietyMemberDetail {
        private Integer userId;
        private String userName;
        private String userAvatar;
        private String role;  // 添加角色字段

        // 修改构造函数，接收 User 对象和角色信息
        public SocietyMemberDetail(User user, String role) {
            this.userId = user.getUserId();
            this.userName = user.getUsername();
            this.userAvatar = user.getAvatarUrl();  // 假设User类有getAvatarUrl方法
            this.role = role;  // 设置角色
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
