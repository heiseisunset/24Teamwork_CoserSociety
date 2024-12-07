package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.CoserWork;

import java.util.List;

public interface CoserWorkService {

    // 发布 Coser 作品
    void publishCoserWork(CoserWork coserWork);

    public List<CoserWork> getAllCoserWorks();

    public List<CoserWork> getCoserWorksByUserId(Integer userId);

    public boolean deleteCoserWork(Integer workId);

    CoserWork getCoserWorkById(Integer workId);
}
