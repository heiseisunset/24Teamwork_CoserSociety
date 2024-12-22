package com.example.demo_dzq.service;

public interface SocietyMemberService {
    // 编辑社团成员角色
    boolean editMemberRole(Integer societyId, Integer userId, String role);
}
