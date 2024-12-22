package com.example.demo_dzq.service;


import com.example.demo_dzq.dto.AddMemberRequestDTO;
import com.example.demo_dzq.dto.SocietyMemberWithDetailsDTO;
import com.example.demo_dzq.dto.SocietyMemberWithUserDTO;
import com.example.demo_dzq.dto.SocietyWithApplicationsDTO;

import java.util.List;

public interface SocietyMemberService {
    // 编辑社团成员角色
    boolean editMemberRole(Integer societyId, Integer userId, String role);

    boolean addMemberToSociety(AddMemberRequestDTO request);

    public SocietyWithApplicationsDTO getSocietyWithApplications(Integer societyId);

    List<SocietyMemberWithUserDTO> getMembersWithUserBySocietyId(Integer societyId);

    // 根据用户ID获取该用户参与的所有社团和社团的详细信息
    SocietyMemberWithDetailsDTO getSocietyMembersAndSocietyInfo(Integer userId);

    // 删除社团成员
    boolean deleteMemberFromSociety(Integer userId, Integer societyId);
}
