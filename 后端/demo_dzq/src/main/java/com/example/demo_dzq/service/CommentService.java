package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.Comment;

import java.util.List;

public interface CommentService {
    // 根据作品ID获取评论列表
    List<Comment> findCommentsByWorkId(Integer workId);

    // 添加评论
    void addComment(Comment comment);

    // 根据作品ID删除所有评论
    void deleteCommentsByWorkId(Integer workId);
}
