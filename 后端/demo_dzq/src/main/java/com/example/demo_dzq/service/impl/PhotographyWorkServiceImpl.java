package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.PhotographyWorkMapper;
import com.example.demo_dzq.mapper.PhotographyCommentsMapper;
import com.example.demo_dzq.pojo.PhotographyComments;
import com.example.demo_dzq.pojo.PhotographyWork;
import com.example.demo_dzq.service.PhotographyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
@Service
public class PhotographyWorkServiceImpl implements PhotographyWorkService {

    @Autowired
    private PhotographyWorkMapper photographyWorkMapper;

    @Autowired
    private PhotographyCommentsMapper photographyCommentsMapper; // 注入评论 Mapper


  // 格式化日期为 "yyyy-MM-dd"
    private String formatCreateTime(LocalDateTime createTime) {
        if (createTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return createTime.format(formatter);
    }


    @Override
    public int addPhotographyWork(PhotographyWork photographyWork) {
        // 如果没有提供 category 则设置为默认值 "photography"
        if (photographyWork.getCategory() == null) {
            photographyWork.setCategory("photography");
        }
        // 插入数据并返回生成的作品ID
        photographyWorkMapper.insertPhotographyWork(photographyWork);
        return photographyWorkMapper.getLastInsertId();
    }

    public List<PhotographyWork> getAllPhotographyWorks() {
        return photographyWorkMapper.selectAllPhotographyWorks();
    }

    @Override
    public List<PhotographyWork> getPhotographyWorksByUserId(Integer userId) {
        // 调用数据库方法获取该用户所有作品
        List<PhotographyWork> works = photographyWorkMapper.selectPhotographyWorksByUserId(userId);

        // 格式化日期
        for (PhotographyWork work : works) {
            if (work != null && work.getCreatedAt() != null) {
                String formattedDate = formatCreateTime(work.getCreatedAt());
                work.setFormattedCreateTime(formattedDate);
            }
        }
        return works;
    }

    @Override
    public boolean deletePhotographyWork(Integer workId) {
        // 首先删除与作品相关的评论
        int commentsDeleted = photographyCommentsMapper.deleteCommentsByWorkId(workId);

        // 然后删除作品
        int workDeleted = photographyWorkMapper.deletePhotographyWork(workId);

        // 如果删除了评论和作品，返回 true
        return commentsDeleted > 0 && workDeleted > 0;
    }


    @Override
    public PhotographyWork getPhotographyWorkById(Integer workId) {
        PhotographyWork work = photographyWorkMapper.getPhotographyWorkById(workId);

        // 格式化日期
        if (work != null && work.getCreatedAt() != null) {
            String formattedDate = formatCreateTime(work.getCreatedAt());
            work.setFormattedCreateTime(formattedDate);
        }

        return work;
    }

    @Override
    public boolean addPhotographyComment(PhotographyComments comment) {
        // 设置评论的创建时间
        comment.setCreatedAt(LocalDateTime.now());
        int rows = photographyCommentsMapper.insertPhotographyComment(comment);
        return rows > 0; // 返回是否成功插入
    }
}
