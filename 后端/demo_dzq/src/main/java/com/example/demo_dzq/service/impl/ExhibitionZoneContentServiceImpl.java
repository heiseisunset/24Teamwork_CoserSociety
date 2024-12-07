package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.ExhibitionZoneContentDTO;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.mapper.OrganizerInfoPostMapper;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import com.example.demo_dzq.service.ExhibitionZoneContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitionZoneContentServiceImpl implements ExhibitionZoneContentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrganizerInfoPostMapper organizerInfoPostMapper;

    @Override
    public ExhibitionZoneContentDTO getExhibitionZoneContent() {
        // 获取所有角色为 'organizer' 的用户
        List<User> organizers = userMapper.findOrganizers();
        // 获取所有主办方信息发布表记录
        List<OrganizerInfoPost> organizerInfoPosts = organizerInfoPostMapper.findAllOrganizerInfoPosts();

        // 封装成 DTO 返回
        ExhibitionZoneContentDTO dto = new ExhibitionZoneContentDTO();
        dto.setOrganizers(organizers);
        dto.setOrganizerInfoPosts(organizerInfoPosts);

        return dto;
    }

    // 获取首页主办方信息
    @Override
    public List<User> getOrganizers() {
        return userMapper.findOrganizers();
    }

    // 获取首页主办方发布信息
    @Override
    public List<OrganizerInfoPost> getOrganizerInfoPosts() {
        return organizerInfoPostMapper.findAllOrganizerInfoPosts();
    }
}
