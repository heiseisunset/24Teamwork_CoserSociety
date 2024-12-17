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
import java.util.List;
import java.util.Map;
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
            @RequestParam("description") String description) {

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

            PhotographyWork photographyWork = new PhotographyWork();
            photographyWork.setUserId(userId);
            photographyWork.setDescription(description);
            photographyWork.setReadaccount(0);
            // 设置作品的创建时间和发布时间
            photographyWork.setCreatedAt(LocalDateTime.now());
            if (photographyWork.getPublishDate() == null) {
                photographyWork.setPublishDate(LocalDateTime.now());  // 默认发布为当前时间
            }
            photographyWork.setImageUrl(imageUrl);
            int workId = photographyWorkService.addPhotographyWork(photographyWork);
            // 创建返回的 DTO 对象
            PhotographyWorkResponseDTO responseDTO = new PhotographyWorkResponseDTO(workId);
            return new Response<>(200, "作品发布成功", responseDTO);
        }
       /* catch (Exception e) {
            return new Response<>(500, "作品发布失败", null);
        }*/
        catch (Exception e) {
            String errorMessage = "作品发布失败: " + e.getMessage();
            return new Response<>(500, errorMessage, null);
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

    // 获取摄影作品的所有评论及评论用户信息
    @GetMapping("/comment/all")
    public Response<List<Map<String, Object>>> getPhotographyComments(
            @RequestParam Integer workId) {
        try {
            // 获取摄影作品的评论列表
            List<PhotographyComments> comments = photographyCommentsService.getCommentsByWorkId(workId);

            if (comments.isEmpty()) {
                return new Response<>(404, "该作品没有评论", null);
            }

            // 获取评论用户的信息并组织数据结构
            List<Map<String, Object>> commentDetails = comments.stream().map(comment -> {
                User user = userService.getUserById(comment.getUserId());
                Map<String, Object> commentData = Map.of(
                        "id", comment.getId(),
                        "userId", comment.getUserId(),
                        "workId", comment.getWorkId(),
                        "content", comment.getContent(),
                        "createTime", comment.getCreateTime(),  // 你可以通过相应的方式设置创建时间
                        "user", Map.of(
                                "userId", user.getUserId(),
                                "username", user.getUsername(),
                                "password", user.getPassword(),  // 如果不需要密码，可以去掉
                                "email", user.getEmail(),
                                "phone", user.getPhone(),
                                "avatarUrl", user.getAvatarUrl(),
                                "bio", user.getBio(),
                                "role", user.getRole(),
                                "createdAt", user.getCreatedAt()
                        )
                );
                return commentData;
            }).collect(Collectors.toList());

            return new Response<>(200, "评论获取成功", commentDetails);
        } catch (Exception e) {
            return new Response<>(500, "获取评论失败: " + e.getMessage(), null);
        }
    }

}
