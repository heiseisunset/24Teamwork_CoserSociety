package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.PhotographyWork;

import java.util.List;

public interface PhotographyWorkService {

    int addPhotographyWork(PhotographyWork photographyWork);

    public List<PhotographyWork> getAllPhotographyWorks();

    // 根据用户ID查询所有发布的摄影作品
    List<PhotographyWork> getPhotographyWorksByUserId(Integer userId);

    boolean deletePhotographyWork(Integer workId); // 删除作品的方法
}
