package com.example.demo_dzq.controller;

import com.example.demo_dzq.dto.OrganizerInfoPostDetailDTO;
import com.example.demo_dzq.dto.OrganizerPostDTO;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import com.example.demo_dzq.service.OrganizerInfoPostDetailService;
import com.example.demo_dzq.service.OrganizerInfoPostService;
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
@RequestMapping("/api/v1/organizerpost")
public class OrganizerInfoPostController {

    @Autowired
    private OrganizerInfoPostService organizerInfoPostService;

    @Autowired
    private OrganizerInfoPostDetailService organizerInfoPostDetailService;

    // 创建主办方发布信息
    @PostMapping("/create")
    public Response<Void> createOrganizerPost(
            @RequestParam("file_image") MultipartFile file_image,  // 上传图片文件
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("userId") Integer userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("location") String location,
            @RequestParam("eventTime") String eventTime) {
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
                imageUrl = "D:/icosImage/" + newFileName; // 根据实际路径调整
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
                    String fileUrl = "D:/icosImage/" + newFileName; // 根据实际路径调整
                    fileUrls.add(fileUrl);
                }
            }

            // 构造主办方发布信息DTO
            OrganizerPostDTO organizerPostDTO = new OrganizerPostDTO();
            organizerPostDTO.setUserId(userId);
            organizerPostDTO.setTitle(title);
            organizerPostDTO.setContent(content);
            organizerPostDTO.setLocation(location);
            organizerPostDTO.setEventTime(eventTime);
            organizerPostDTO.setMainImageUrl(imageUrl);  // 可能有单个图片
            organizerPostDTO.setDetailImages(fileUrls);  // 可能有多个文件

            organizerInfoPostService.createOrganizerPost(organizerPostDTO);
            // 返回成功的响应，data为null
            return new Response<>(200, "Organizer post created successfully", null);
        } catch (Exception e) {
            // 返回错误的响应，data为null
            return new Response<>(500, "Organizer post created unsuccessfully" ,null);
        }
    }

    // 根据当前登录用户ID查询主办方发布的所有信息
    @GetMapping("/all")
    public Response<List<OrganizerInfoPost>> getOrganizerPostsByUserId(@RequestParam Integer userId) {
        try {
            List<OrganizerInfoPost> posts = organizerInfoPostService.getOrganizerPostsByUserId(userId);
            return new Response<>(200, "Organizer posts retrieved successfully", posts);
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

    //主办方信息详情页
    @GetMapping("/details")
    public Response<OrganizerInfoPostDetailDTO> getOrganizerInfoPostDetails(@RequestParam Integer postId) {
        // 获取主办方发布信息的详细内容
        OrganizerInfoPostDetailDTO dto = organizerInfoPostDetailService.getOrganizerPostDetails(postId);

        // 如果没有找到对应的发布信息，返回错误响应
        if (dto == null || dto.getOrganizerInfoPost() == null) {
            return new Response<>(404, "Organizer post not found", null);
        }

        // 找到相关信息，返回成功的响应
        return new Response<>(200, "Success", dto);
    }
}
