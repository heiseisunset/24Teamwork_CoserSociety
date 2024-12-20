package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.PhotographyCommentsMapper;
import com.example.demo_dzq.pojo.PhotographyComments;
import com.example.demo_dzq.service.PhotographyCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PhotographyCommentsServiceImpl implements PhotographyCommentsService {
    @Autowired
    private PhotographyCommentsMapper photographyCommentsMapper;

    // 格式化日期为 "yyyy-MM-dd"
    private String formatCreateTime(LocalDateTime createTime) {
        if (createTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return createTime.format(formatter);
    }

    @Override
    public List<PhotographyComments> getCommentsByWorkId(Integer workId) {
        List<PhotographyComments> comments = photographyCommentsMapper.getCommentsByWorkId(workId);

        // 遍历评论列表并设置格式化后的日期
        for (PhotographyComments comment : comments) {
            if (comment.getCreateTime() != null) {
                String formattedDate = formatCreateTime(comment.getCreateTime());
                comment.setFormattedCreateTime(formattedDate);
            }
        }

        return comments;
    }
}
