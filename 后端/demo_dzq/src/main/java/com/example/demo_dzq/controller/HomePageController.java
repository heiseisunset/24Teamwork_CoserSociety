package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.pojo.HomePageContentResponse;
import com.example.demo_dzq.service.HomePageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/homepage-content")
public class HomePageController {

    @Autowired
    private HomePageContentService homePageContentService;

    // 获取首页所有分区内容
    @GetMapping("/all")
    public Response<HomePageContentResponse> getHomePageContent() {
        HomePageContentResponse contentResponse = homePageContentService.getHomePageContent();
        return new Response<>(200, "首页内容获取成功", contentResponse);
    }

    // 获取首页Coser作品
    @GetMapping("/coser-works")
    public Response<Object> getCoserWorks() {
        return new Response<>(200, "Coser作品获取成功", homePageContentService.getCoserWorks());
    }

    // 获取首页摄影作品
    @GetMapping("/photography-works")
    public Response<Object> getPhotographyWorks() {
        return new Response<>(200, "摄影作品获取成功", homePageContentService.getPhotographyWorks());
    }

    // 获取首页主办方信息
    @GetMapping("/organizer-info")
    public Response<Object> getOrganizerInfo() {
        return new Response<>(200, "主办方信息获取成功", homePageContentService.getOrganizerInfoPosts());
    }

    // 获取首页社团信息
    @GetMapping("/societies")
    public Response<Object> getSocieties() {
        return new Response<>(200, "社团信息获取成功", homePageContentService.getSocieties());
    }
}
