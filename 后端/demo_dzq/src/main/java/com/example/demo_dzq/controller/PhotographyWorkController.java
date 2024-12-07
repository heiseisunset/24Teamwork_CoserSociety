package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.PhotographyWorkResponseDTO;
import com.example.demo_dzq.pojo.PhotographyWork;
import com.example.demo_dzq.service.PhotographyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/photographywork")
public class PhotographyWorkController {

    @Autowired
    private PhotographyWorkService photographyWorkService;

    // 创建摄影作品
    @PostMapping("/post")
    public Response<PhotographyWorkResponseDTO> addPhotographyWork(@RequestBody PhotographyWork photographyWork) {
        // 设置作品的创建时间和发布时间
        photographyWork.setCreatedAt(LocalDateTime.now());
        if (photographyWork.getPublishDate() == null) {
            photographyWork.setPublishDate(LocalDateTime.now());  // 默认发布为当前时间
        }

        try {
            int workId = photographyWorkService.addPhotographyWork(photographyWork);
            // 创建返回的 DTO 对象
            PhotographyWorkResponseDTO responseDTO = new PhotographyWorkResponseDTO(workId);
            return new Response<>(200, "作品发布成功", responseDTO);
        } catch (Exception e) {
            return new Response<>(500, "作品发布失败", null);
        }
    }

    // 获取当前登录用户发布的所有摄影作品
    @GetMapping("/user")
    public Response<List<PhotographyWork>> getPhotographyWorksByUserId(@RequestParam Integer userId) {
        try {
            // 获取该用户发布的所有作品
            List<PhotographyWork> photographyWorks = photographyWorkService.getPhotographyWorksByUserId(userId);

            // 如果没有作品，返回提示信息
            if (photographyWorks.isEmpty()) {
                return new Response<>(404, "没有找到该用户发布的作品", null);
            }

            return new Response<>(200, "获取作品成功", photographyWorks);
        } catch (Exception e) {
            return new Response<>(500, "获取作品失败: " + e.getMessage(), null);
        }
    }

    // 删除摄影作品
    @DeleteMapping("/delete")
    public Response<String> deletePhotographyWork(@RequestParam Integer workId) {
        try {
            boolean isDeleted = photographyWorkService.deletePhotographyWork(workId);
            if (isDeleted) {
                return new Response<>(200, "作品删除成功", null);
            } else {
                return new Response<>(404, "作品未找到", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "删除作品失败: " + e.getMessage(), null);
        }
    }
}
