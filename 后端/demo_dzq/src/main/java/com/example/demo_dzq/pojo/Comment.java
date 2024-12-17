package com.example.demo_dzq.pojo;

import java.util.Date;

public class Comment {
    private Integer id; // 评论ID
    private Integer userId; // 用户ID
    private Integer workId; // 作品ID
    private String content; // 评论内容
    private Date createTime; // 评论时间
    // 新增的用户字段
    private User user;  // 存储评论用户的信息

    // getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // 构造函数、getter 和 setter
    public Comment() {}

    public Comment(Integer id, Integer userId, Integer workId, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.workId = workId;
        this.content = content;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
