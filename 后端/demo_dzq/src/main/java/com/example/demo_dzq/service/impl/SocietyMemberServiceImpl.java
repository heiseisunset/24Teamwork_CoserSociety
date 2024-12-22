package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.AddMemberRequestDTO;
import com.example.demo_dzq.dto.SocietyApplicationWithUserDTO;
import com.example.demo_dzq.dto.SocietyMemberWithUserDTO;
import com.example.demo_dzq.dto.SocietyWithApplicationsDTO;
import com.example.demo_dzq.mapper.SocietyApplicationMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.pojo.SocietyApplication;
import com.example.demo_dzq.pojo.SocietyMember;
import com.example.demo_dzq.service.SocietyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_dzq.pojo.User;

import java.util.List;

@Service
public class SocietyMemberServiceImpl implements SocietyMemberService {

    @Autowired
    private SocietyMemberMapper memberMapper;

    @Autowired
    private SocietyApplicationMapper societyApplicationMapper;

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

    @Override
    public SocietyWithApplicationsDTO getSocietyWithApplications(Integer societyId) {
        // 查询社团成员信息
        List<SocietyMember> members = memberMapper.selectMembersBySocietyId(societyId);

        // 查询社团申请信息
        List<SocietyApplication> applications = societyApplicationMapper.selectApplicationsBySocietyId(societyId);

        // 将成员转换为 DTO
        List<SocietyMemberWithUserDTO> memberDTOs = members.stream().map(member -> {
            User user = userMapper.findById(member.getUserId()); // 假设有一个方法根据 userId 查询 User
            SocietyMemberWithUserDTO dto = new SocietyMemberWithUserDTO();
            dto.setSocietyId(member.getSocietyId());
            dto.setUserId(member.getUserId());
            dto.setRole(member.getRole());
            dto.setJoinedAt(member.getJoinedAt());
            dto.setUser(user); // 将 User 信息填充到 DTO 中
            return dto;
        }).toList();

        // 将申请转换为 DTO
        List<SocietyApplicationWithUserDTO> applicationDTOs = applications.stream().map(application -> {
            User user = userMapper.findById(application.getUserId()); // 假设有一个方法根据 userId 查询 User
            SocietyApplicationWithUserDTO dto = new SocietyApplicationWithUserDTO();
            dto.setApplicationId(application.getApplicationId());
            dto.setSocietyId(application.getSocietyId());
            dto.setUserId(application.getUserId());
            dto.setStatus(application.getStatus());
            dto.setCreatedAt(application.getCreatedAt());
            dto.setUpdatedAt(application.getUpdatedAt());
            dto.setUser(user); // 将 User 信息填充到 DTO 中
            return dto;
        }).toList();

        // 封装结果
        SocietyWithApplicationsDTO dto = new SocietyWithApplicationsDTO();
        dto.setSocietyMembers(memberDTOs);
        dto.setSocietyApplications(applicationDTOs);

        return dto;
    }


    @Override
    public List<SocietyMemberWithUserDTO> getMembersWithUserBySocietyId(Integer societyId) {
        // 1. 获取所有社团成员（不包含用户信息）
        List<SocietyMemberWithUserDTO> members = memberMapper.selectMembersWithUserBySocietyId(societyId);

        // 2. 遍历成员列表，根据 userId 填充 User 信息
        for (SocietyMemberWithUserDTO member : members) {
            // 获取 userId
            Integer userId = member.getUserId();

            // 通过 userId 查询用户信息
            User user = userMapper.findById(userId);

            // 设置 User 信息到 DTO 中
            member.setUser(user);
        }

        return members;
    }

}
