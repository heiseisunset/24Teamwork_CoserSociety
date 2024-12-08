package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.PhotographyComments;

import java.util.List;

public interface PhotographyCommentsService {
    List<PhotographyComments> getCommentsByWorkId(Integer workId);
}
