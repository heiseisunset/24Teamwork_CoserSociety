package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.SocietyInfoPostMapper;
import com.example.demo_dzq.pojo.SocietyInfoPost;
import com.example.demo_dzq.service.SocietyInfoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocietyInfoPostServiceImpl implements SocietyInfoPostService {

    @Autowired
    private SocietyInfoPostMapper societyInfoPostMapper;

    @Override
    public boolean addSocietyInfoPost(SocietyInfoPost post) {
        return societyInfoPostMapper.insertSocietyInfoPost(post) > 0;
    }

    @Override
    public List<SocietyInfoPost> getSocietyInfoPostsBySocietyId(Integer societyId) {
        return societyInfoPostMapper.getSocietyInfoPostsBySocietyId(societyId);
    }
}
