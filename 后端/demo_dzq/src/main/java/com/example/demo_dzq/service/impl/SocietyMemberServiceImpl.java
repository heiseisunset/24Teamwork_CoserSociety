package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.service.SocietyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocietyMemberServiceImpl implements SocietyMemberService {

    @Autowired
    private SocietyMemberMapper memberMapper;

    /**
     * 更新社团成员角色
     * @param societyId 社团ID
     * @param userId 用户ID
     * @param role 新角色（"member" 或 "admin"）
     * @return 更新是否成功
     */
    @Override
    public boolean editMemberRole(Integer societyId, Integer userId, String role) {
        // 校验角色是否合法
        if (!role.equals("member") && !role.equals("admin")) {
            throw new IllegalArgumentException("Role must be either 'member' or 'admin'.");
        }

        // 更新社团成员角色
        int updatedRows = memberMapper.updateMemberRole(societyId, userId, role);
        return updatedRows > 0;
    }
}
