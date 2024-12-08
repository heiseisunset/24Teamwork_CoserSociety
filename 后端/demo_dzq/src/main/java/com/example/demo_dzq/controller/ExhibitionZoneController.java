package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.ExhibitionZoneContentDTO;
import com.example.demo_dzq.pojo.CoserWork;
import com.example.demo_dzq.pojo.PhotographyWork;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.ExhibitionZoneContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_dzq.service.UserService;
import com.example.demo_dzq.service.CoserWorkService;
import com.example.demo_dzq.service.PhotographyWorkService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exhibition-zone")
public class ExhibitionZoneController {

    @Autowired
    private ExhibitionZoneContentService exhibitionZoneContentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoserWorkService coserWorkService;

    @Autowired
    private PhotographyWorkService photographyWorkService;

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

    // 获取主办方信息详情页
    @GetMapping("/organizer-details")
    public Response<Object> getOrganizerDetails(@RequestParam("userId") Integer userId) {
        // 获取用户信息
        User user = userService.getUserById(userId);
        if (user == null) {
            return new Response<>(404, "用户未找到", null);
        }

        // 获取该用户的所有cos作品
        List<CoserWork> coserWorks = coserWorkService.getCoserWorksByUserId(userId);

        // 获取该用户的所有摄影作品
        List<PhotographyWork> photographyWorks = photographyWorkService.getPhotographyWorksByUserId(userId);

        // 将数据打包返回
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("coserWorks", coserWorks);
        result.put("photographyWorks", photographyWorks);

        return new Response<>(200, "主办方信息详情页获取成功", result);
    }
}
