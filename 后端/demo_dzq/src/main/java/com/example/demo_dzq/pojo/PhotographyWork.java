package com.example.demo_dzq.pojo;

import java.time.LocalDateTime;

public class PhotographyWork {
    private Integer workId;
    private Integer userId;
    private String imageUrl;
    private String description;
    private LocalDateTime publishDate;
    private LocalDateTime createdAt;
    private Integer readaccount; // 新增字段
    private String formattedCreateTime; // 格式化后的时间

    // Getter 和 Setter
    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getReadaccount() {
        return readaccount;
    }

    public void setReadaccount(Integer readaccount) {
        this.readaccount = readaccount;
    }

    public String getFormattedCreateTime() {
        return formattedCreateTime;
    }

    public void setFormattedCreateTime(String formattedCreateTime) {
        this.formattedCreateTime = formattedCreateTime;
    }
}
