package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.CommentMapper;
import com.example.demo_dzq.pojo.Comment;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

//    @Override
//    public List<Comment> findCommentsByWorkId(Integer workId) {
//        return commentMapper.findCommentsByWorkId(workId);
//    }

    // 格式化日期为 "yyyy-MM-dd"
    private String formatCreateTime(Date createTime) {
        if (createTime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(createTime);
    }

    @Override
    public List<Comment> findCommentsByWorkId(Integer workId) {
        List<Comment> comments = commentMapper.findCommentsByWorkId(workId);

        for (Comment comment : comments) {
            // 设置评论对应的用户信息
            User user = commentMapper.findUserById(comment.getUserId());
            comment.setUser(user);  // 假设 Comment 类有 `user` 字段

            // 格式化 createTime 为 "yyyy-MM-dd"
            Date originalCreateTime = comment.getCreateTime();
            String formattedCreateTime = formatCreateTime(originalCreateTime);
            comment.setFormattedCreateTime(formattedCreateTime); // 假设 Comment 有 setFormattedCreateTime 方法
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
