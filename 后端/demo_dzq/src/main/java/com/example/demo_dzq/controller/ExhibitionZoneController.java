package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.ExhibitionZoneContentDTO;
import com.example.demo_dzq.service.ExhibitionZoneContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exhibition-zone")
public class ExhibitionZoneController {

    @Autowired
    private ExhibitionZoneContentService exhibitionZoneContentService;

    // 获取漫展分区内容
    @GetMapping("/all")
    public Response<ExhibitionZoneContentDTO> getExhibitionZoneContent() {
        ExhibitionZoneContentDTO contentDTO = exhibitionZoneContentService.getExhibitionZoneContent();
        return new Response<>(200, "漫展分区内容获取成功", contentDTO);
    }

    // 获取首页主办方信息
    @GetMapping("/organizer")
    public Response<Object> getOrganizerInfo() {
        return new Response<>(200, "主办方信息获取成功", exhibitionZoneContentService.getOrganizers());
    }

    // 获取首页主办方发布信息
    @GetMapping("/organizer-info")
    public Response<Object> getSocieties() {
        return new Response<>(200, "主办方发布信息获取成功", exhibitionZoneContentService.getOrganizerInfoPosts());
    }
}
