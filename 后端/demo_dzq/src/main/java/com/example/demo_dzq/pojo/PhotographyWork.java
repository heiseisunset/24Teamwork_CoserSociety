package com.example.demo_dzq.pojo;

import java.time.LocalDateTime;

public class PhotographyWork {
    private Integer workId;             // 作品ID
    private Integer userId;             // 用户ID
    private String title;               // 作品标题
    private String originalWork;        // 原作名称
    private String characterName;       // 角色名称
    private String content;             // 作品介绍
    private String imageUrl;            // 作品图片的 URL
    private LocalDateTime publishDate;  // 发布日期
    private Integer readCount;          // 阅读次数
    private LocalDateTime createdAt;    // 创建时间
    private String formattedCreateTime; // 格式化后的创建时间
    private String category;            // 新增字段: 作品类别

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalWork() {
        return originalWork;
    }

    public void setOriginalWork(String originalWork) {
        this.originalWork = originalWork;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFormattedCreateTime() {
        return formattedCreateTime;
    }

    public void setFormattedCreateTime(String formattedCreateTime) {
        this.formattedCreateTime = formattedCreateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // 可选：重写 toString() 方法
    @Override
    public String toString() {
        return "PhotographyWork{" +
                "workId=" + workId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", originalWork='" + originalWork + '\'' +
                ", characterName='" + characterName + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishDate=" + publishDate +
                ", readCount=" + readCount +
                ", createdAt=" + createdAt +
                ", formattedCreateTime='" + formattedCreateTime + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
