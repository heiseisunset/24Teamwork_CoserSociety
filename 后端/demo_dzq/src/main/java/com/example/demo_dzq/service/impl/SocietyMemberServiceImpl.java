package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.AddMemberRequestDTO;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.pojo.SocietyMember;
import com.example.demo_dzq.service.SocietyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_dzq.pojo.User;

@Service
public class SocietyMemberServiceImpl implements SocietyMemberService {

    @Autowired
    private SocietyMemberMapper memberMapper;


    @Autowired
    private UserMapper userMapper;
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

    @Override
    public boolean addMemberToSociety(AddMemberRequestDTO request) {
        // 查找用户是否存在
        User user = userMapper.findByPhoneNumberAndUserName(request.getPhoneNumber(), request.getUserName());
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        // 检查用户是否已经是该社团的成员
        SocietyMember existingMember = memberMapper.findBySocietyIdAndUserId(request.getSocietyId(), user.getUserId());
        if (existingMember != null) {
            throw new IllegalArgumentException("User is already a member of this society.");
        }

        // 添加用户到社团成员表
        SocietyMember member = new SocietyMember();
        member.setSocietyId(request.getSocietyId());
        member.setUserId(user.getUserId());
        member.setRole(request.getRole());
        boolean isAdded = memberMapper.insertSocietyMember(member) > 0;
        return isAdded;
    }
}
