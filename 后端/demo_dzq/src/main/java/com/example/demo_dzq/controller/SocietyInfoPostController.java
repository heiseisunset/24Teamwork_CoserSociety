package com.example.demo_dzq.controller;

import com.example.demo_dzq.pojo.SocietyInfoPost;
import com.example.demo_dzq.service.SocietyInfoPostService;
import com.example.demo_dzq.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/society-info")
public class SocietyInfoPostController {

    @Autowired
    private SocietyInfoPostService societyInfoPostService;

    // 发布社团信息接口，支持单个和多个文件上传
    @PostMapping("/publish")
    public Response<String> publishSocietyInfoPost(
            @RequestParam("file") MultipartFile file_image,  // 上传单张图片文件
            @RequestParam("files") MultipartFile[] files,  // 上传多个文件
            @RequestParam("societyId") Integer societyId,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        try {
            // 确保目录存在
            Path uploadDir = Paths.get("D:/icosImage");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // 创建目录
            }

            // 处理单个文件（file_image）
            String imageUrl = null;
            if (!file_image.isEmpty()) {
                String originalFileName = file_image.getOriginalFilename();
                String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : ".jpg";
                String newFileName = System.currentTimeMillis() + fileExtension;
                Path targetPath = uploadDir.resolve(newFileName);
                file_image.transferTo(targetPath);
                imageUrl = "D:/icosImage/" + newFileName; // 根据你项目的实际访问路径调整
            }

            // 处理多个文件（files）
            List<String> fileUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : ".jpg";
                    String newFileName = System.currentTimeMillis() + fileExtension;
                    Path targetPath = uploadDir.resolve(newFileName);
                    file.transferTo(targetPath);
                    String fileUrl = "D:/icosImage/" + newFileName; // 根据你项目的实际访问路径调整
                    fileUrls.add(fileUrl);
                }
            }

            // 将多个文件URL合并为一个字符串（通过逗号分隔）
            String detailImages = String.join(",", fileUrls);

            // 构造SocietyInfoPost对象
            SocietyInfoPost post = new SocietyInfoPost();
            post.setSocietyId(societyId);
            post.setTitle(title);
            post.setContent(content);
            post.setMainImageUrl(imageUrl);  // 可能有单个图片
            post.setDetailImages(detailImages);  // 如果有多个详情图，前端传递的详情图URL之间用分隔符隔开
            post.setCreatedAt(java.time.LocalDateTime.now());  // 设置当前时间

            // 发布社团信息
            boolean result = societyInfoPostService.addSocietyInfoPost(post);

            // 返回成功响应
            if (result) {
                return new Response<>(200, "社团信息发布成功", null);
            } else {
                return new Response<>(500, "社团信息发布失败", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "文件上传或发布信息失败: " + e.getMessage(), null);
        }
    }

//    // 根据社团ID获取所有发布的社团信息
//    @GetMapping("/all")
//    public Response<List<SocietyInfoPost>> getSocietyInfoPosts(@RequestParam Integer societyId) {
//        try {
//            List<SocietyInfoPost> posts = societyInfoPostService.getSocietyInfoPostsBySocietyId(societyId);
//            return new Response<>(200, "社团信息获取成功", posts);
//        } catch (Exception e) {
//            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
//        }
//    }
}
