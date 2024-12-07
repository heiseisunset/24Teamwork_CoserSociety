package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.OrganizerInfoDTO;
import com.example.demo_dzq.mapper.OrganizerInfoMapper;
import com.example.demo_dzq.pojo.OrganizerInfo;
import com.example.demo_dzq.service.OrganizerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrganizerInfoServiceImpl implements OrganizerInfoService {

    @Autowired
    private OrganizerInfoMapper organizerInfoMapper;

    @Override
    @Transactional
    public void createOrUpdateOrganizerInfo(OrganizerInfoDTO organizerInfoDTO) {
        // 检查是否已有相同 user_id 的主办方信息
        OrganizerInfo existingOrganizerInfo = organizerInfoMapper.findByUserId(organizerInfoDTO.getUserId());

        // 如果已存在，更新主办方信息
        if (existingOrganizerInfo != null) {
            existingOrganizerInfo.setName(organizerInfoDTO.getName());
            existingOrganizerInfo.setHeadquartersCity(organizerInfoDTO.getHeadquartersCity());
            existingOrganizerInfo.setDescription(organizerInfoDTO.getDescription());
            existingOrganizerInfo.setLogoUrl(organizerInfoDTO.getLogoUrl());
            // 不更新 created_at 字段
            organizerInfoMapper.updateOrganizerInfo(existingOrganizerInfo);
        } else {
            // 如果不存在，插入新的主办方信息
            OrganizerInfo newOrganizerInfo = new OrganizerInfo();
            newOrganizerInfo.setUserId(organizerInfoDTO.getUserId());
            newOrganizerInfo.setName(organizerInfoDTO.getName());
            newOrganizerInfo.setHeadquartersCity(organizerInfoDTO.getHeadquartersCity());
            newOrganizerInfo.setDescription(organizerInfoDTO.getDescription());
            newOrganizerInfo.setLogoUrl(organizerInfoDTO.getLogoUrl());
            // 插入时设置 created_at
            newOrganizerInfo.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

            organizerInfoMapper.insertOrganizerInfo(newOrganizerInfo);
        }
    }

    public List<OrganizerInfo> getAllOrganizerInfos() {
        return organizerInfoMapper.selectAllOrganizerInfos();
    }
}

