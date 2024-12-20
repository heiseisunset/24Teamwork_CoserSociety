package com.example.demo_dzq.pojo;

import java.time.LocalDateTime;

public class PhotographyComments {
    private Integer commentId;
    private Integer workId;
    private Integer userId;
    private String content;
    private LocalDateTime createdAt;
    private String formattedCreateTime; // 格式化后的时间


    // Getter and Setter for commentId
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    // Getter and Setter for workId
    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    // Getter and Setter for userId
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and Setter for createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Add getId() method for commentId
    public Integer getId() {
        return commentId;
    }

    // Add getCreateTime() method for createdAt
    public LocalDateTime getCreateTime() {
        return createdAt;
    }

    public String getFormattedCreateTime() {
        return formattedCreateTime;
    }

    public void setFormattedCreateTime(String formattedCreateTime) {
        this.formattedCreateTime = formattedCreateTime;
    }
}
