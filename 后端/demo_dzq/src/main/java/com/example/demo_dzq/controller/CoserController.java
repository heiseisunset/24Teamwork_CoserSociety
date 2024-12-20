package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.CoserWorkDetailDTO;
import com.example.demo_dzq.pojo.CoserWork;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.pojo.Comment;
import com.example.demo_dzq.service.CommentService;
import com.example.demo_dzq.service.UserService;
import com.example.demo_dzq.service.CoserWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/coser")
public class CoserController {

    @Autowired
    private CoserWorkService coserWorkService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // 修改后的发布接口，接受文件
    @PostMapping("/publish")
    public Response<String> publishCoserWork(
            @RequestParam("file") MultipartFile file,  // 上传图片文件
            @RequestParam("userId") Integer userId,
            @RequestParam("title") String title,
            @RequestParam("originalWork") String originalWork,
            @RequestParam("characterName") String characterName,
            @RequestParam("content") String content,
            @RequestParam("photographer") String photographer,
            @RequestParam("makeupArtist") String makeupArtist,
            @RequestParam("postProduction") String postProduction){
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

            // 2. 创建CoserWork对象
            CoserWork coserWork = new CoserWork();
            coserWork.setUserId(userId);
            coserWork.setTitle(title);
            coserWork.setOriginalWork(originalWork);
            coserWork.setCharacterName(characterName);
            coserWork.setContent(content);
            coserWork.setImageUrl(imageUrl);
            coserWork.setPhotographer(photographer);
            coserWork.setMakeupArtist(makeupArtist);
            coserWork.setPostProduction(postProduction);

            // 发布Coser作品
            coserWorkService.publishCoserWork(coserWork);

            // 返回成功响应
            return new Response<>(200, "Coser work published successfully", null);
        } catch (IOException e) {
            return new Response<>(500, "Error occurred while uploading file: " + e.getMessage(), null);
        }
        catch (Exception e) {
            String errorMessage = "作品发布失败: " + e.getMessage();
            return new Response<>(500, errorMessage, null);
        }
    }

    // 获取当前登录用户的所有Coser作品
    @GetMapping("/user")
    public Response<List<CoserWork>> getCoserWorksByUserId(@RequestParam Integer userId) {
        try {
            // 获取用户信息
            User user = userService.getUserById(userId);
            if (user == null) {
                return new Response<>(404, "User not found", null);
            }

            // 查询用户的所有 Coser 作品
            List<CoserWork> coserWorks = coserWorkService.getCoserWorksByUserId(userId);

            // 填充用户相关的用户名字段
            for (CoserWork work : coserWorks) {
                // 查询摄影师的用户名
                if (work.getPhotographerId() != null) {
                    User photographer = userService.getUserById(work.getPhotographerId());
                    if (photographer != null) {
                        work.setPhotographer(photographer.getUsername());
                    }
                }

                // 查询化妆师的用户名
                if (work.getMakeupArtistId() != null) {
                    User makeupArtist = userService.getUserById(work.getMakeupArtistId());
                    if (makeupArtist != null) {
                        work.setMakeupArtist(makeupArtist.getUsername());
                    }
                }

                // 查询后期制作的用户名
                if (work.getPostProductionId() != null) {
                    User postProduction = userService.getUserById(work.getPostProductionId());
                    if (postProduction != null) {
                        work.setPostProduction(postProduction.getUsername());
                    }
                }
            }

            return new Response<>(200, "Coser works retrieved successfully", coserWorks);
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }


    @DeleteMapping("/work")
    public Response<String> deleteCoserWork(@RequestParam Integer workId) {
        try {
            coserWorkService.deleteCoserWork(workId);
            return new Response<>(200, "Coser作品删除成功", null);
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

    @GetMapping("/details")
    public Response<CoserWorkDetailDTO> getCoserWorkDetails(@RequestParam Integer workId, @RequestParam Integer userId) {
        try {
            // 查询用户信息
            User user = userService.getUserById(userId);
            if (user == null) {
                return new Response<>(404, "User not found", null);
            }

            // 查询作品信息
            CoserWork coserWork = coserWorkService.getCoserWorkById(workId);
            if (coserWork == null) {
                return new Response<>(404, "Coser work not found", null);
            }

            User photographer = userService.getUserById(coserWork.getPhotographerId());
            coserWork.setPhotographer(photographer.getUsername());


            User makeupArtist = userService.getUserById(coserWork.getMakeupArtistId());
            coserWork.setMakeupArtist(makeupArtist.getUsername());

            User postProduction = userService.getUserById(coserWork.getPostProductionId());
            coserWork.setPostProduction(postProduction.getUsername());

            // 查询评论信息
            List<Comment> comments = commentService.findCommentsByWorkId(workId);

            // 组装返回数据
            CoserWorkDetailDTO coserWorkDetailDTO = new CoserWorkDetailDTO(user, coserWork, comments);
            return new Response<>(200, "Details retrieved successfully", coserWorkDetailDTO);

        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

}
