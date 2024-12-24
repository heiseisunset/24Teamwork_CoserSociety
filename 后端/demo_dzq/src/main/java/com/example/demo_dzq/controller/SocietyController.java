package com.example.demo_dzq.controller;

import com.example.demo_dzq.dto.*;
import com.example.demo_dzq.pojo.Society;
import com.example.demo_dzq.service.SocietyMemberService;
import com.example.demo_dzq.service.SocietyApplicationService;
import com.example.demo_dzq.service.SocietyService;
import com.example.demo_dzq.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/societies")
public class SocietyController {

    private static final Logger logger = LoggerFactory.getLogger(SocietyController.class);

    @Autowired
    private SocietyService societyService;

    @Autowired
    private SocietyMemberService societyMemberService;

    @Autowired
    private SocietyApplicationService societyApplicationService;

    @GetMapping("/detail")
    public Response<SocietyDetailResponseDTO> getSocietyDetail(@RequestParam Integer societyId) {
        try {
            // 调用服务层获取社团详细信息
            SocietyDetailResponseDTO response = societyService.getSocietyDetail(societyId);
            return new Response<>(200, "Success", response);
        } catch (Exception e) {
            logger.error("Failed to fetch society details for societyId: {}", societyId, e);
            return new Response<>(500, "Failed to fetch society details", null);
        }
    }

    @PostMapping("/create")
    public Response<String> createSociety(
            @RequestParam("file") MultipartFile file,  // 上传的图片文件
            @RequestParam("name") String name,
            @RequestParam("founderName") String founderName,
            @RequestParam("mainCity") String mainCity,
            @RequestParam("description") String description) {
        try {
            // 确保目录存在
            Path uploadDir = Paths.get("D:/icosImage");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // 创建目录
            }

            // 获取文件扩展名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName != null && originalFileName.contains(".")
                    ? originalFileName.substring(originalFileName.lastIndexOf("."))
                    : ".jpg";

            // 生成唯一文件名，避免冲突
            String newFileName = System.currentTimeMillis() + fileExtension;

            // 文件保存路径
            Path targetPath = uploadDir.resolve(newFileName);

            // 保存文件到磁盘
            file.transferTo(targetPath.toFile());

            // 构造文件的访问URL (假设你的应用能够通过 HTTP 访问 D:/icosImage)
            String imageUrl = "D:/icosImage/" + newFileName; // 根据你项目的实际访问路径调整

            // 构造 Society 对象
            Society society = new Society();
            society.setName(name);
            society.setFounderName(founderName);
            society.setMainCity(mainCity);
            society.setDescription(description);
            society.setLogoUrl(imageUrl);

            // 调用 Service 方法创建社团并添加到成员关系表
            boolean isSuccess = societyService.createSocietyWithFounderNameAndAddMember(society);

            if (isSuccess) {
                return new Response<>(200, "Society created successfully!", null);
            } else {
                return new Response<>(500, "Failed to create society.", null);
            }
        } catch (IOException e) {
            logger.error("Error occurred while uploading file", e);
            return new Response<>(500, "Error occurred while uploading file: " + e.getMessage(), null);
        } catch (Exception e) {
            logger.error("Failed to create society", e);
            return new Response<>(500, "Failed to create society: " + e.getMessage(), null);
        }
    }

    @PostMapping("/addMember")
    public Response<String> addMember(@RequestBody AddMemberRequestDTO request) {
        try {
            boolean isSuccess = societyMemberService.addMemberToSociety(request);
            if (isSuccess) {
                return new Response<>(200, "User added to society successfully.", null);
            }
            return new Response<>(500, "Failed to add user to society.", null);
        } catch (IllegalArgumentException e) {
            logger.warn("Error adding member to society: {}", e.getMessage());
            return new Response<>(400, "Error: " + e.getMessage(), null);
        } catch (Exception e) {
            logger.error("Unexpected error while adding member", e);
            return new Response<>(500, "Unexpected error: " + e.getMessage(), null);
        }
    }

    @GetMapping("/members")
    public Response<SocietyWithApplicationsDTO> getSocietyMembersWithUsers(@RequestBody SocietyRequestDTO societyRequestDTO) {
        try {
            Integer societyId = societyRequestDTO.getSocietyId();
            // 参数校验
            if (societyId == null || societyId <= 0) {
                return new Response<>(400, "Invalid societyId provided.", null);
            }

            // 查询社团成员和申请记录
            List<SocietyMemberWithUserDTO> members = societyMemberService.getMembersWithUserBySocietyId(societyId);
            List<SocietyApplicationWithUserDTO> applications = societyApplicationService.getPendingApplicationsWithUserBySocietyId(societyId);

            // 如果没有找到社团成员或者申请记录，返回提示信息
            if (members == null || members.isEmpty()) {
                logger.warn("No members found for societyId: {}", societyId);
                return new Response<>(404, "No members found for the given societyId.", null);
            }

            if (applications == null || applications.isEmpty()) {
                logger.info("No pending applications found for societyId: {}", societyId);
            }

            // 封装响应数据
            SocietyWithApplicationsDTO responseData = new SocietyWithApplicationsDTO(members, applications);
            return new Response<>(200, "Successfully retrieved society members and applications with user details.", responseData);

        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching members and applications", e);
            // 捕获所有异常并返回系统错误响应
            return new Response<>(500, "An unexpected error occurred: " + e.getMessage(), null);
        }
    }

    // 获取用户参与的社团信息
    @GetMapping("/participation")
    public Response<SocietyMemberWithDetailsDTO> getSocietyMembersAndSocietyInfo(@RequestParam Integer userId) {
        try {
            // 获取用户参与的社团信息及社团详情
            SocietyMemberWithDetailsDTO result = societyMemberService.getSocietyMembersAndSocietyInfo(userId);
            return new Response<>(200, "获取社团信息成功", result);
        } catch (Exception e) {
            logger.error("Error occurred while fetching society participation details", e);
            return new Response<>(500, "获取社团信息失败: " + e.getMessage(), null);
        }
    }

    @DeleteMapping("/leave")
    public Response<String> leaveSociety(@RequestParam Integer userId, @RequestParam Integer societyId) {
        try {
            // 调用服务层逻辑删除社团成员记录
            boolean success = societyMemberService.deleteMemberFromSociety(userId, societyId);

            if (success) {
                return new Response<>(200, "退出社团成功", null);
            } else {
                return new Response<>(500, "退出社团失败", null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while a user leaves a society", e);
            return new Response<>(500, "系统错误: " + e.getMessage(), null);
        }
    }
}
