package com.example.demo_dzq.service;

import com.example.demo_dzq.dto.OrganizerInfoPostDetailDTO;

public interface OrganizerInfoPostDetailService {

    /**
     * 根据 postId 获取主办方发布信息的详细内容，包括评论及评论者的用户信息。
     *
     * @param postId 发布信息的ID
     * @return 包含发布信息、评论和评论者用户信息的DTO对象
     */
    OrganizerInfoPostDetailDTO getOrganizerPostDetails(Integer postId);
}
