package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.PhotographyWorkMapper;
import com.example.demo_dzq.pojo.PhotographyWork;
import com.example.demo_dzq.service.PhotographyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographyWorkServiceImpl implements PhotographyWorkService {

    @Autowired
    private PhotographyWorkMapper photographyWorkMapper;

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
}
