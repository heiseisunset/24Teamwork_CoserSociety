package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.pojo.*;
import com.example.demo_dzq.mapper.CoserWorkMapper;
import com.example.demo_dzq.mapper.PhotographyWorkMapper;
import com.example.demo_dzq.mapper.OrganizerInfoPostMapper;
import com.example.demo_dzq.mapper.SocietyMapper;
import com.example.demo_dzq.service.HomePageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageContentServiceImpl implements HomePageContentService {

    @Autowired
    private CoserWorkMapper coserWorkMapper;

    @Autowired
    private PhotographyWorkMapper photographyWorkMapper;

    @Autowired
    private OrganizerInfoPostMapper organizerInfoPostMapper;

    @Autowired
    private SocietyMapper societyMapper;

    @Override
    public HomePageContentResponse getHomePageContent() {
        HomePageContentResponse response = new HomePageContentResponse();

        // 获取每个表的数据
        List<CoserWork> coserWorks = coserWorkMapper.selectAllCoserWorks();
        List<PhotographyWork> photographyWorks = photographyWorkMapper.selectAllPhotographyWorks();
        List<OrganizerInfoPost> organizerInfoPosts = organizerInfoPostMapper.findAllOrganizerInfoPosts();
        List<Society> societies = societyMapper.findAllSocieties();

        // 设置响应数据
        response.setCoserWorks(coserWorks);
        response.setPhotographyWorks(photographyWorks);
        response.setOrganizerInfoPosts(organizerInfoPosts);
        response.setSocieties(societies);

        return response;
    }

    // 获取所有 Coser 作品
    public List<CoserWork> getCoserWorks() {
        return coserWorkMapper.selectAllCoserWorks();
    }

    // 获取所有 摄影作品
    public List<PhotographyWork> getPhotographyWorks() {
        return photographyWorkMapper.selectAllPhotographyWorks();
    }

    // 获取所有 主办方发布信息
    public List<OrganizerInfoPost> getOrganizerInfoPosts() {
        return organizerInfoPostMapper.findAllOrganizerInfoPosts();
    }

    // 获取所有 社团信息
    public List<Society> getSocieties() {
        return societyMapper.findAllSocieties();
    }
}
