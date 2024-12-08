package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.PhotographyWorkMapper;
import com.example.demo_dzq.mapper.PhotographyCommentsMapper;
import com.example.demo_dzq.pojo.PhotographyComments;
import com.example.demo_dzq.pojo.PhotographyWork;
import com.example.demo_dzq.service.PhotographyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhotographyWorkServiceImpl implements PhotographyWorkService {

    @Autowired
    private PhotographyWorkMapper photographyWorkMapper;

    @Autowired
    private PhotographyCommentsMapper photographyCommentsMapper; // 注入评论 Mapper

    @Override
    public int addPhotographyWork(PhotographyWork photographyWork) {
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
        return photographyWorkMapper.selectPhotographyWorksByUserId(userId);
    }

    @Override
    public boolean deletePhotographyWork(Integer workId) {
        // 调用 Mapper 删除作品
        int rowsAffected = photographyWorkMapper.deletePhotographyWork(workId);
        return rowsAffected > 0; // 如果删除成功，返回 true
    }

    @Override
    public PhotographyWork getPhotographyWorkById(Integer workId) {
        return photographyWorkMapper.getPhotographyWorkById(workId);
    }

    @Override
    public boolean addPhotographyComment(PhotographyComments comment) {
        // 设置评论的创建时间
        comment.setCreatedAt(LocalDateTime.now());
        int rows = photographyCommentsMapper.insertPhotographyComment(comment);
        return rows > 0; // 返回是否成功插入
    }
}
