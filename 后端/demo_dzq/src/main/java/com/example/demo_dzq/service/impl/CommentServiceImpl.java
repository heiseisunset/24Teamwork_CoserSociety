package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.CommentMapper;
import com.example.demo_dzq.pojo.Comment;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

//    @Override
//    public List<Comment> findCommentsByWorkId(Integer workId) {
//        return commentMapper.findCommentsByWorkId(workId);
//    }

    @Override
    public List<Comment> findCommentsByWorkId(Integer workId) {
        List<Comment> comments = commentMapper.findCommentsByWorkId(workId);

        // 获取每个评论的用户信息
        for (Comment comment : comments) {
            User user = commentMapper.findUserById(comment.getUserId());
            comment.setUser(user);  // 假设 Comment 类有一个 `user` 字段
        }

        return comments;
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteCommentsByWorkId(Integer workId) {
        commentMapper.deleteCommentsByWorkId(workId);
    }
}
