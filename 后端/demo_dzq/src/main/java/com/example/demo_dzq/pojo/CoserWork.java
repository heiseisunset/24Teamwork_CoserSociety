package com.example.demo_dzq.pojo;

import java.sql.Timestamp;

public class CoserWork {

    private Integer workId;                // 作品ID
    private Integer userId;                // 发布者用户ID
    private String title;                  // 作品标题
    private String originalWork;          // 原作名称
    private String characterName;         // 角色名称
    private String content;               // 作品介绍
    private String imageUrl;              // 作品图片URL
    private Timestamp publishDate;        // 发布日期
    private Integer readCount;            // 阅读次数
    private Integer photographerId;       // 摄影师用户ID
    private Integer makeupArtistId;       // 化妆师用户ID
    private Integer postProductionId;     // 后期制作用户ID
    private Timestamp createdAt;          // 创建时间

    // 新增字段，接收前端传递的用户名
    private String photographer;           // 摄影师用户名
    private String makeupArtist;           // 化妆师用户名
    private String postProduction;         // 后期制作用户名

    // Getter 和 Setter 方法
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

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(Integer photographerId) {
        this.photographerId = photographerId;
    }

    public Integer getMakeupArtistId() {
        return makeupArtistId;
    }

    public void setMakeupArtistId(Integer makeupArtistId) {
        this.makeupArtistId = makeupArtistId;
    }

    public Integer getPostProductionId() {
        return postProductionId;
    }

    public void setPostProductionId(Integer postProductionId) {
        this.postProductionId = postProductionId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // 新增 Getter 和 Setter 方法，获取传入的用户名
    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getMakeupArtist() {
        return makeupArtist;
    }

    public void setMakeupArtist(String makeupArtist) {
        this.makeupArtist = makeupArtist;
    }

    public String getPostProduction() {
        return postProduction;
    }

    public void setPostProduction(String postProduction) {
        this.postProduction = postProduction;
    }
}
