package com.example.demo_dzq.pojo;

import java.sql.Timestamp;

public class OrganizerInfoPost {

    private int postId;  // 发布信息ID
    private int organizerId;  // 主办方ID
    private String title;  // 发布信息标题
    private String content;  // 发布信息内容
    private String mainImageUrl;  // 主图的URL
    private String detailImages;  // 详情图的URL列表
    private Timestamp createdAt;  // 创建时间

    // Getter 和 Setter 方法

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(String detailImages) {
        this.detailImages = detailImages;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
