package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.CoserWorkMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.mapper.CommentMapper;  // 导入 CommentMapper
import com.example.demo_dzq.pojo.CoserWork;
import com.example.demo_dzq.service.CoserWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoserWorkServiceImpl implements CoserWorkService {

    @Autowired
    private CoserWorkMapper coserWorkMapper;

    @Autowired
    private UserMapper userMapper;  // 用于查询用户ID

    @Autowired
    private CommentMapper commentMapper;  // 注入 CommentMapper

    @Override
    @Transactional
    public void publishCoserWork(CoserWork coserWork) {
        // 根据用户名查询用户ID
        Integer photographerId = userMapper.getUserIdByUsername(coserWork.getPhotographer());
        Integer makeupArtistId = userMapper.getUserIdByUsername(coserWork.getMakeupArtist());
        Integer postProductionId = userMapper.getUserIdByUsername(coserWork.getPostProduction());

        if (photographerId == null || makeupArtistId == null || postProductionId == null) {
            throw new RuntimeException("One or more user roles not found");
        }

        // 设置 CoserWork 对象中的 userId（photographerId, makeupArtistId, postProductionId）
        coserWork.setPhotographerId(photographerId);
        coserWork.setMakeupArtistId(makeupArtistId);
        coserWork.setPostProductionId(postProductionId);

        // 设置发布日期和创建时间
        coserWork.setPublishDate(Timestamp.valueOf(LocalDateTime.now()));
        coserWork.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        // 初始化 readCount 为 0
        coserWork.setReadCount(0);

        // 插入作品数据
        coserWorkMapper.insertCoserWork(coserWork);
    }

    public List<CoserWork> getAllCoserWorks() {
        return coserWorkMapper.selectAllCoserWorks();
    }

    // 根据用户ID查询所有Coser作品
    public List<CoserWork> getCoserWorksByUserId(Integer userId) {
        return coserWorkMapper.findCoserWorksByUserId(userId);
    }

    // 删除Coser作品
    @Transactional
    public boolean deleteCoserWork(Integer workId) {
        try {
            // 第一步：删除与该作品相关的所有评论
            commentMapper.deleteCommentsByWorkId(workId);

            // 第二步：删除作品
            int result = coserWorkMapper.deleteCoserWork(workId);

            // 如果没有删除任何记录，抛出异常或返回删除失败
            if (result == 0) {
                throw new RuntimeException("未找到对应的Coser作品，删除失败");
            }

            // 如果删除成功，返回true
            return result > 0;
        } catch (Exception e) {
            // 如果出现异常，抛出一个运行时异常
            throw new RuntimeException("删除Coser作品时发生异常", e);
        }
    }

    @Override
    public CoserWork getCoserWorkById(Integer workId) {
        return coserWorkMapper.getCoserWorkById(workId);
    }
}
