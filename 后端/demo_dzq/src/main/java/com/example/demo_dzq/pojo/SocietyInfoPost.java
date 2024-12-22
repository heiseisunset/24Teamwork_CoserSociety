package com.example.demo_dzq.pojo;

import java.time.LocalDateTime;

public class SocietyInfoPost {
    private Integer postId;          // 发布信息ID
    private Integer societyId;       // 社团ID
    private String title;            // 信息标题
    private String content;          // 信息内容
    private String mainImageUrl;     // 主图的URL
    private String detailImages;     // 更多详情图的URL列表，多个图片URL之间用分隔符隔开
    private LocalDateTime createdAt; // 创建时间

    // Getter 和 Setter 方法
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Integer societyId) {
        this.societyId = societyId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
