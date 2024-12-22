package com.example.demo_dzq.service;


import com.example.demo_dzq.dto.AddMemberRequestDTO;
public interface SocietyMemberService {
    // 编辑社团成员角色
    boolean editMemberRole(Integer societyId, Integer userId, String role);

    boolean addMemberToSociety(AddMemberRequestDTO request);
}
