package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.pojo.Comment;
import com.example.demo_dzq.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coser/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

//    // 获取指定作品的所有评论
//    @GetMapping("/work/{workId}")
//    public Response<List<Comment>> getCommentsByWorkId(@PathVariable Integer workId) {
//        try {
//            List<Comment> comments = commentService.findCommentsByWorkId(workId);
//            return new Response<>(200, "Comments retrieved successfully", comments);
//        } catch (Exception e) {
//            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
//        }
//    }

    @PostMapping("/add")
    public Response<String> addComment(@RequestBody Comment comment) {
        try {
            commentService.addComment(comment);
            return new Response<>(200, "Comment added successfully", null);
        } catch (Exception e) {
            // 打印异常详细信息到日志
            e.printStackTrace(); // 记录详细的异常信息
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

    // 根据作品ID获取所有评论
    @GetMapping("/get")
    public Response<List<Comment>> getCommentsByWorkId(@RequestParam Integer workId) {
        try {
            // 查询该作品的所有评论
            List<Comment> comments = commentService.findCommentsByWorkId(workId);
            if (comments.isEmpty()) {
                return new Response<>(404, "No comments found for the work", null);
            }
            return new Response<>(200, "Comments retrieved successfully", comments);
        } catch (Exception e) {
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }
}
