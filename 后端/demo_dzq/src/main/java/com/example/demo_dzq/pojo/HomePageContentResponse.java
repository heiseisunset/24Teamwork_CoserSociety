package com.example.demo_dzq.pojo;

import java.util.List;

public class HomePageContentResponse {

    private List<CoserWork> coserWorks;             // Coser作品列表
    private List<PhotographyWork> photographyWorks; // 摄影作品列表
    private List<OrganizerInfoPost> organizerInfoPosts; // 主办方发布信息列表
    private List<Society> societies;               // 社团信息列表

    // Getter 和 Setter

    public List<CoserWork> getCoserWorks() {
        return coserWorks;
    }

    public void setCoserWorks(List<CoserWork> coserWorks) {
        this.coserWorks = coserWorks;
    }

    public List<PhotographyWork> getPhotographyWorks() {
        return photographyWorks;
    }

    public void setPhotographyWorks(List<PhotographyWork> photographyWorks) {
        this.photographyWorks = photographyWorks;
    }

    public List<OrganizerInfoPost> getOrganizerInfoPosts() {
        return organizerInfoPosts;
    }

    public void setOrganizerInfoPosts(List<OrganizerInfoPost> organizerInfoPosts) {
        this.organizerInfoPosts = organizerInfoPosts;
    }

    public List<Society> getSocieties() {
        return societies;
    }

    public void setSocieties(List<Society> societies) {
        this.societies = societies;
    }

    // toString 方法（可选）
    @Override
    public String toString() {
        return "HomePageContentResponse{" +
                "coserWorks=" + coserWorks +
                ", photographyWorks=" + photographyWorks +
                ", organizerInfoPosts=" + organizerInfoPosts +
                ", societies=" + societies +
                '}';
    }
}
