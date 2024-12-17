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

    // 添加评论
    @PostMapping("/add")
    public Response<String> addComment(@RequestBody Comment comment) {
        try {
            commentService.addComment(comment);
            return new Response<>(200, "Comment added successfully", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }

    // 根据作品ID获取所有评论
    @GetMapping("/get")
    public Response<List<Comment>> getCommentsByWorkId(@RequestParam Integer workId) {
        try {
            List<Comment> comments = commentService.findCommentsByWorkId(workId);
            if (comments.isEmpty()) {
                return new Response<>(404, "No comments found for the work", null);
            }
            return new Response<>(200, "Comments retrieved successfully", comments);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(500, "Error occurred: " + e.getMessage(), null);
        }
    }
}
