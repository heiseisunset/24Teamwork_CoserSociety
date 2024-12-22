package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.SocietyInfoPost;

import java.util.List;

public interface SocietyInfoPostService {

    // 添加社团信息发布
    boolean addSocietyInfoPost(SocietyInfoPost post);

    // 根据社团ID获取所有发布的社团信息
    List<SocietyInfoPost> getSocietyInfoPostsBySocietyId(Integer societyId);
}
