package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.PhotographyWorkResponseDTO;
import com.example.demo_dzq.pojo.PhotographyComments;
import com.example.demo_dzq.pojo.PhotographyWork;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.PhotographyCommentsService;
import com.example.demo_dzq.service.PhotographyWorkService;
import com.example.demo_dzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/photographywork")
public class PhotographyWorkController {

    @Autowired
    private PhotographyWorkService photographyWorkService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotographyCommentsService photographyCommentsService;

    // 创建摄影作品
    @PostMapping("/post")
    public Response<PhotographyWorkResponseDTO> addPhotographyWork(
            @RequestParam("file") MultipartFile file,  // 上传图片文件
            @RequestParam("userId") Integer userId,
            @RequestParam("title") String title,
            @RequestParam("originalWork") String originalWork,
            @RequestParam("characterName") String characterName,
            @RequestParam("content") String content) {

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

            // 创建摄影作品对象并设置字段
            PhotographyWork photographyWork = new PhotographyWork();
            photographyWork.setUserId(userId);
            photographyWork.setTitle(title);
            photographyWork.setOriginalWork(originalWork);
            photographyWork.setCharacterName(characterName);
            photographyWork.setContent(content);
            photographyWork.setImageUrl(imageUrl);
            photographyWork.setReadCount(0);
            photographyWork.setCreatedAt(LocalDateTime.now());
            photographyWork.setPublishDate(LocalDateTime.now());  // 默认发布为当前时间

            int workId = photographyWorkService.addPhotographyWork(photographyWork);

            // 创建返回的 DTO 对象
            PhotographyWorkResponseDTO responseDTO = new PhotographyWorkResponseDTO(workId);
            return new Response<>(200, "作品发布成功", responseDTO);
        } catch (Exception e) {
            return new Response<>(500, "作品发布失败" + e.getMessage(), null);
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

    // 获取摄影作品详情页
    @GetMapping("/details")
    public Response<Map<String, Object>> getPhotographyWorkDetails(
            @RequestParam Integer workId,
            @RequestParam Integer authorId) {
        try {
            // 获取摄影作品信息
            PhotographyWork photographyWork = photographyWorkService.getPhotographyWorkById(workId);
            if (photographyWork == null) {
                return new Response<>(404, "摄影作品未找到", null);
            }

            // 获取作者信息
            User author = userService.getUserById(authorId);
            if (author == null) {
                return new Response<>(404, "作者信息未找到", null);
            }

            // 获取评论及评论者信息
            List<PhotographyComments> comments = photographyCommentsService.getCommentsByWorkId(workId);
            List<User> commentUsers = comments.stream()
                    .map(comment -> userService.getUserById(comment.getUserId()))
                    .collect(Collectors.toList());

            // 构造返回结果
            Map<String, Object> result = Map.of(
                    "photographyWork", photographyWork,
                    "author", author,
                    "comments", comments,
                    "commentUsers", commentUsers
            );
            return new Response<>(200, "详情获取成功", result);
        } catch (Exception e) {
            return new Response<>(500, "获取详情失败: " + e.getMessage(), null);
        }
    }

    // 添加摄影作品评论
    @PostMapping("/comment/add")
    public Response<String> addPhotographyComment(@RequestBody PhotographyComments comment) {
        try {
            // 保存评论
            boolean isAdded = photographyWorkService.addPhotographyComment(comment);
            if (isAdded) {
                return new Response<>(200, "评论发布成功", null);
            } else {
                return new Response<>(500, "评论发布失败", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "评论发布异常: " + e.getMessage(), null);
        }
    }

    @GetMapping("/comment/all")
    public Response<List<Map<String, Object>>> getPhotographyComments(@RequestParam Integer workId) {
        try {
            List<PhotographyComments> comments = photographyCommentsService.getCommentsByWorkId(workId);

            if (comments.isEmpty()) {
                return new Response<>(404, "该作品没有评论", null);
            }

            List<Map<String, Object>> commentDetails = comments.stream().map(comment -> {
                User user = userService.getUserById(comment.getUserId());
                String content = Optional.ofNullable(comment.getContent()).orElse("评论内容为空");

                Map<String, Object> commentData = new HashMap<>();
                commentData.put("id", comment.getId());
                commentData.put("userId", comment.getUserId());
                commentData.put("workId", comment.getWorkId());
                commentData.put("content", content);
                commentData.put("createTime", comment.getCreateTime());
                commentData.put("formattedCreateTime", comment.getFormattedCreateTime());

                if (user != null) {
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("userId", user.getUserId());
                    userData.put("username", user.getUsername());
                    userData.put("email", user.getEmail());
                    userData.put("phone", Optional.ofNullable(user.getPhone()).orElse("无"));
                    userData.put("avatarUrl", Optional.ofNullable(user.getAvatarUrl()).orElse("无"));
                    userData.put("bio", user.getBio());
                    userData.put("role", user.getRole());
                    userData.put("createdAt", user.getCreatedAt());
                    commentData.put("user", userData);
                } else {
                    commentData.put("user", "用户信息不存在");
                }

                return commentData;
            }).collect(Collectors.toList());


            return new Response<>(200, "评论获取成功", commentDetails);
        } catch (Exception e) {
            e.printStackTrace(); // 打印完整的异常信息
            return new Response<>(500, "获取评论失败: " + e.getMessage(), null);
        }
    }

}

