package com.example.demo_dzq.service;

import com.example.demo_dzq.dto.OrganizerPostDTO;
import com.example.demo_dzq.pojo.OrganizerInfoPost;

import java.util.List;

public interface OrganizerInfoPostService {
    // 创建主办方发布信息
    void createOrganizerPost(OrganizerPostDTO organizerPostDTO);

    public List<OrganizerInfoPost> getOrganizerPostsByUserId(Integer userId);
}
