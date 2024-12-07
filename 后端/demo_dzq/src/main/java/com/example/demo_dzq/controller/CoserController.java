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

    @PostMapping("/publish")
    public Response<String> publishCoserWork(@RequestBody CoserWork coserWork) {
        try {
            coserWorkService.publishCoserWork(coserWork);
            return new Response<>(200, "Coser work published successfully", null);
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

    // 获取当前登录用户的所有Coser作品
    @GetMapping("/user")
    public Response<List<CoserWork>> getCoserWorksByUserId(@RequestParam  Integer userId) {
        try {
            // 从数据库查询当前用户ID对应的所有Coser作品
            List<CoserWork> coserWorks = coserWorkService.getCoserWorksByUserId(userId);

            // 返回成功的Response
            return new Response<>(200, "Coser works retrieved successfully", coserWorks);
        } catch (Exception e) {
            // 返回失败的Response
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
