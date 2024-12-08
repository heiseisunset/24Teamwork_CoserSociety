package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.OrganizerInfoPostDetailDTO;
import com.example.demo_dzq.mapper.CommentForOrganizerInfoPostMapper;
import com.example.demo_dzq.mapper.OrganizerInfoPostMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.pojo.CommentForOrganizerInfoPost;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.OrganizerInfoPostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

@Service
public class OrganizerInfoPostDetailServiceImpl implements OrganizerInfoPostDetailService {

    @Autowired
    private OrganizerInfoPostMapper organizerInfoPostMapper;

    @Autowired
    private CommentForOrganizerInfoPostMapper commentForOrganizerInfoPostMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public OrganizerInfoPostDetailDTO getOrganizerPostDetails(Integer postId) {
        // 查询主办方发布信息
        OrganizerInfoPost organizerInfoPost = organizerInfoPostMapper.findByPostId(postId);

        // 查询该发布信息的所有评论
        List<CommentForOrganizerInfoPost> comments = commentForOrganizerInfoPostMapper.findByPostId(postId);

        // 查询评论者的用户信息并转换为Map
        List<Map<String, Object>> commentUsers = comments.stream()
                .map(comment -> {
                    User user = userMapper.findById(comment.getUserId());
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("userId", user.getUserId());
                    userMap.put("username", user.getUsername());
                    userMap.put("email", user.getEmail());
                    userMap.put("phone", user.getPhone());
                    userMap.put("avatarUrl", user.getAvatarUrl());
                    userMap.put("bio", user.getBio());
                    userMap.put("role", user.getRole());
                    userMap.put("createdAt", user.getCreatedAt());
                    return userMap;
                })
                .collect(Collectors.toList());

        // 构建 DTO 返回
        OrganizerInfoPostDetailDTO dto = new OrganizerInfoPostDetailDTO();
        dto.setOrganizerInfoPost(organizerInfoPost);
        dto.setComments(comments);
        dto.setCommentUsers(commentUsers);  // 设置评论用户的详细信息

        return dto;
    }
}
