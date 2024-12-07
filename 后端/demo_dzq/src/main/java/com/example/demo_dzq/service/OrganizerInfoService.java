package com.example.demo_dzq.service;

import com.example.demo_dzq.dto.OrganizerInfoDTO;
import com.example.demo_dzq.pojo.OrganizerInfo;

import java.util.List;

public interface OrganizerInfoService {

    /**
     * 创建或更新主办方信息
     * 如果传入的 user_id 对应的主办方信息存在，则更新该记录；
     * 如果不存在，则插入新的主办方信息。
     *
     * @param organizerInfoDTO 主办方信息 DTO
     */
    void createOrUpdateOrganizerInfo(OrganizerInfoDTO organizerInfoDTO);

    public List<OrganizerInfo> getAllOrganizerInfos();
}
