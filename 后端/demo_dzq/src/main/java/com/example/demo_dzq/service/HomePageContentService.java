package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.*;

import java.util.List;

public interface HomePageContentService {
    // 获取首页分区内容
    HomePageContentResponse getHomePageContent();

    // 获取所有 Coser 作品
    public List<CoserWork> getCoserWorks();

    // 获取所有 摄影作品
    public List<PhotographyWork> getPhotographyWorks();

    // 获取所有 主办方发布信息
    public List<OrganizerInfoPost> getOrganizerInfoPosts();

    // 获取所有 社团信息
    public List<Society> getSocieties();
}
