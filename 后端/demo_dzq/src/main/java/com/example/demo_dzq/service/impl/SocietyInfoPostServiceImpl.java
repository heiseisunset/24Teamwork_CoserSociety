package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.SocietyInfoPostMapper;
import com.example.demo_dzq.pojo.SocietyInfoPost;
import com.example.demo_dzq.service.SocietyInfoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocietyInfoPostServiceImpl implements SocietyInfoPostService {

    @Autowired
    private SocietyInfoPostMapper postMapper;

    @Override
    public boolean addSocietyInfoPost(SocietyInfoPost post) {
        return postMapper.insertSocietyInfoPost(post) > 0;
    }
}
