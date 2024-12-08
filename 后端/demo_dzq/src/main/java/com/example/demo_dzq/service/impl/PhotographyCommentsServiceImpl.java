package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.PhotographyCommentsMapper;
import com.example.demo_dzq.pojo.PhotographyComments;
import com.example.demo_dzq.service.PhotographyCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographyCommentsServiceImpl implements PhotographyCommentsService {
    @Autowired
    private PhotographyCommentsMapper photographyCommentsMapper;

    @Override
    public List<PhotographyComments> getCommentsByWorkId(Integer workId) {
        return photographyCommentsMapper.getCommentsByWorkId(workId);
    }
}
