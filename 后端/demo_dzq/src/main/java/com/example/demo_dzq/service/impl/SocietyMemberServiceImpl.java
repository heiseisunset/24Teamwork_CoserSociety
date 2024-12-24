package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.*;
import com.example.demo_dzq.mapper.SocietyApplicationMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.pojo.Society;
import com.example.demo_dzq.pojo.SocietyApplication;
import com.example.demo_dzq.pojo.SocietyMember;
import com.example.demo_dzq.service.SocietyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_dzq.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyMemberServiceImpl implements SocietyMemberService {

    private static final Logger logger = LoggerFactory.getLogger(SocietyMemberServiceImpl.class);

    @Autowired
    private SocietyMemberMapper memberMapper;

    @Autowired
    private SocietyApplicationMapper societyApplicationMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 更新社团成员角色
     *
     * @param societyId 社团ID
     * @param userId    用户ID
     * @param role      新角色（"member" 或 "admin"）
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
        return memberMapper.insertSocietyMember(member) > 0;
    }

    @Override
    public SocietyWithApplicationsDTO getSocietyWithApplications(Integer societyId) {
        // 查询社团成员信息
        List<SocietyMember> members = memberMapper.selectMembersBySocietyId(societyId);
        logger.debug("Members found: {}", members.size());

        // 查询社团待处理申请信息
        List<SocietyApplicationWithUserDTO> applications = societyApplicationMapper.selectPendingApplicationsBySocietyId(societyId);
        logger.debug("Pending applications found: {}", applications.size());

        // 将成员转换为 DTO
        List<SocietyMemberWithUserDTO> memberDTOs = members.stream().map(member -> {
            User user = userMapper.findById(member.getUserId());
            SocietyMemberWithUserDTO dto = new SocietyMemberWithUserDTO();
            dto.setSocietyId(member.getSocietyId());
            dto.setUserId(member.getUserId());
            dto.setRole(member.getRole());
            dto.setJoinedAt(member.getJoinedAt());
            dto.setUser(user);
            return dto;
        }).toList();

        // 如果没有待处理的申请，输出提示信息
        if (applications.isEmpty()) {
            logger.debug("No pending applications found for the given societyId: {}", societyId);
        }

        // 封装结果，返回空的申请列表而不是 null
        SocietyWithApplicationsDTO dto = new SocietyWithApplicationsDTO();
        dto.setSocietyMembers(memberDTOs);
        dto.setSocietyApplications(applications); // 即使没有申请，也会返回空列表

        return dto;
    }

    @Override
    public List<SocietyMemberWithUserDTO> getMembersWithUserBySocietyId(Integer societyId) {
        List<SocietyMemberWithUserDTO> members = memberMapper.selectMembersWithUserBySocietyId(societyId);
        for (SocietyMemberWithUserDTO member : members) {
            User user = userMapper.findById(member.getUserId());
            member.setUser(user);
        }
        return members;
    }

    @Override
    public SocietyMemberWithDetailsDTO getSocietyMembersAndSocietyInfo(Integer userId) {
        List<SocietyMember> societyMembers = memberMapper.getSocietyMembersByUserId(userId);
        SocietyMemberWithDetailsDTO dto = new SocietyMemberWithDetailsDTO();
        List<Society> societies = new ArrayList<>();

        for (SocietyMember member : societyMembers) {
            Society society = memberMapper.getSocietyById(member.getSocietyId());
            societies.add(society);
        }

        dto.setSociety(societies);
        dto.setSocietyMembers(societyMembers);
        return dto;
    }

    @Override
    public boolean deleteMemberFromSociety(Integer userId, Integer societyId) {
        int result = memberMapper.deleteMemberFromSociety(userId, societyId);
        return result > 0;
    }
}
