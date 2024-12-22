package com.example.demo_dzq.service;


import com.example.demo_dzq.dto.AddMemberRequestDTO;
import com.example.demo_dzq.dto.SocietyMemberWithUserDTO;
import com.example.demo_dzq.dto.SocietyWithApplicationsDTO;

import java.util.List;

public interface SocietyMemberService {
    // 编辑社团成员角色
    boolean editMemberRole(Integer societyId, Integer userId, String role);

    boolean addMemberToSociety(AddMemberRequestDTO request);

    public SocietyWithApplicationsDTO getSocietyWithApplications(Integer societyId);

    List<SocietyMemberWithUserDTO> getMembersWithUserBySocietyId(Integer societyId);
}
