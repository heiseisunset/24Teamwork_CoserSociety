package com.example.demo_dzq.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HelpRequest {

    private Integer requestId;        // 求助请求 ID
    private Integer userId;          // 发布者用户 ID
    private String title;            // 求助标题
    private LocalDate eventDate;     // 活动日期
    private Integer duration;        // 活动持续天数
    private String imageUrl;         // 活动相关图片 URL
    private String description;      // 任务简介
    private String phone;            // 联系电话
    private String contact;          // 其他联系方式（如 QQ/微信）
    private BigDecimal fee;          // 任务费用
    private String city;             // 活动所在城市
    private LocalDateTime createdAt; // 创建时间，默认当前时间

    // Getter 和 Setter
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // 默认构造函数
    public HelpRequest() {
    }

    // 带参构造函数
    public HelpRequest(Integer userId, String title, LocalDate eventDate, Integer duration, String imageUrl,
                       String description, String phone, String contact, BigDecimal fee, String city) {
        this.userId = userId;
        this.title = title;
        this.eventDate = eventDate;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.description = description;
        this.phone = phone;
        this.contact = contact;
        this.fee = fee;
        this.city = city;
        this.createdAt = LocalDateTime.now();  // 默认设置为当前时间
    }
}
