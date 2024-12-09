package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.OrganizerPostDTO;
import com.example.demo_dzq.mapper.OrganizerInfoMapper;
import com.example.demo_dzq.mapper.OrganizerInfoPostMapper;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import com.example.demo_dzq.pojo.OrganizerInfo;
import com.example.demo_dzq.service.OrganizerInfoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrganizerInfoPostServiceImpl implements OrganizerInfoPostService {

    @Autowired
    private OrganizerInfoMapper organizerInfoMapper;

    @Autowired
    private OrganizerInfoPostMapper organizerInfoPostMapper;

    @Override
    @Transactional
    public void createOrganizerPost(OrganizerPostDTO organizerPostDTO) {
        // 通过 userId 查找对应的 organizer_id
        OrganizerInfo organizerInfo = organizerInfoMapper.findByUserId(organizerPostDTO.getUserId());

        if (organizerInfo == null) {
            throw new RuntimeException("Organizer not found for userId: " + organizerPostDTO.getUserId());
        }

        // 创建主办方发布信息对象
        OrganizerInfoPost organizerInfoPost = new OrganizerInfoPost();
        organizerInfoPost.setOrganizerId(organizerInfo.getOrganizerId());
        organizerInfoPost.setTitle(organizerPostDTO.getTitle());
        organizerInfoPost.setContent(organizerPostDTO.getContent());
        organizerInfoPost.setMainImageUrl(organizerPostDTO.getMainImageUrl());


        // 设置地点和活动时间
        organizerInfoPost.setLocation(organizerPostDTO.getLocation());
        organizerInfoPost.setEventTime(organizerPostDTO.getEventTime());

        // 将 detailImages 列表转换为字符串，用逗号分隔
        String detailImages = String.join(",", organizerPostDTO.getDetailImages());
        organizerInfoPost.setDetailImages(detailImages);

        // 设置创建时间
        organizerInfoPost.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        // 插入主办方发布信息
        organizerInfoPostMapper.insertOrganizerInfoPost(organizerInfoPost);


    }

    public List<OrganizerInfoPost> getOrganizerPostsByUserId(Integer userId) {
        // 第一步：通过用户ID查询主办方ID
        OrganizerInfo organizerInfo = organizerInfoMapper.findByUserId(userId);
        if (organizerInfo == null) {
            throw new RuntimeException("User does not belong to any organizer.");
        }

        // 第二步：根据主办方ID查询该主办方的所有发布信息
        return organizerInfoPostMapper.findPostsByOrganizerId(organizerInfo.getOrganizerId());
    }
}
