package com.example.demo_dzq.dto;

import com.example.demo_dzq.pojo.CommentForOrganizerInfoPost;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import java.util.List;
import java.util.Map;

public class OrganizerInfoPostDetailDTO {

    // 主办方发布的信息
    private OrganizerInfoPost organizerInfoPost;

    // 评论列表
    private List<CommentForOrganizerInfoPost> comments;

    // 评论者信息列表（使用Map存储每个评论者的部分信息）
    private List<Map<String, Object>> commentUsers;

    // Getter 和 Setter 方法

    public OrganizerInfoPost getOrganizerInfoPost() {
        return organizerInfoPost;
    }

    public void setOrganizerInfoPost(OrganizerInfoPost organizerInfoPost) {
        this.organizerInfoPost = organizerInfoPost;
    }

    public List<CommentForOrganizerInfoPost> getComments() {
        return comments;
    }

    public void setComments(List<CommentForOrganizerInfoPost> comments) {
        this.comments = comments;
    }

    public List<Map<String, Object>> getCommentUsers() {
        return commentUsers;
    }

    public void setCommentUsers(List<Map<String, Object>> commentUsers) {
        this.commentUsers = commentUsers;
    }
}
