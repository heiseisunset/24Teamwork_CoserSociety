package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.OrganizerInfoDTO;
import com.example.demo_dzq.pojo.CoserWork;
import com.example.demo_dzq.service.OrganizerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerInfoController {

    @Autowired
    private OrganizerInfoService organizerInfoService;

    // 创建或更新主办方信息
    @PostMapping("/createOrUpdate")
    public Response<String> createOrUpdateOrganizerInfo(
            @RequestParam("file") MultipartFile file,  // 上传图片文件
            @RequestParam("userId") Integer userId,
            @RequestParam("name") String name,
            @RequestParam("headquartersCity") String headquartersCity,
            @RequestParam("description") String description){
        try {
            // 确保目录存在
            Path uploadDir = Paths.get("D:/icosImage");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // 创建目录
            }

            // 获取文件扩展名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : ".jpg";

            // 生成唯一的文件名，避免文件名冲突
            String newFileName = System.currentTimeMillis() + fileExtension;

            // 目标路径
            Path targetPath = uploadDir.resolve(newFileName);

            // 保存文件到磁盘
            file.transferTo(targetPath);

            // 构造文件的访问URL (假设你的应用能够通过 HTTP 访问 D:/icosImage)
            String imageUrl = "D:/icosImage/" + newFileName; // 根据你项目的实际访问路径调整

            //构造对象
            OrganizerInfoDTO organizerInfoDTO = new OrganizerInfoDTO();
            organizerInfoDTO.setUserId(userId);
            organizerInfoDTO.setName(name);
            organizerInfoDTO.setHeadquartersCity(headquartersCity);
            organizerInfoDTO.setDescription(description);
            organizerInfoDTO.setLogoUrl(imageUrl);

            organizerInfoService.createOrUpdateOrganizerInfo(organizerInfoDTO);
            // 返回成功的响应，data 为 null
            return new Response<>(200, "Organizer information created or updated successfully", null);
        } catch (Exception e) {
            // 返回错误响应
            return new Response<>(500, "Organizer information created or updated unsuccessfully" , null);
        }
    }
}
