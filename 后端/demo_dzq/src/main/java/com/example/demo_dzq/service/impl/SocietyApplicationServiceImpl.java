package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.dto.SocietyApplicationWithUserDTO;
import com.example.demo_dzq.mapper.SocietyApplicationMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.pojo.SocietyApplication;
import com.example.demo_dzq.pojo.SocietyMember;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.SocietyApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SocietyApplicationServiceImpl implements SocietyApplicationService {

    @Autowired
    private SocietyApplicationMapper applicationMapper;

    @Autowired
    private SocietyMemberMapper memberMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean submitApplication(SocietyApplication application) {
        application.setStatus("pending");  // 设置默认值为 'pending'
        return applicationMapper.insertApplication(application) > 0;
    }

    @Override
    public List<SocietyApplication> getPendingApplications(Integer societyId) {
        return applicationMapper.findPendingApplicationsBySocietyId(societyId);
    }

    @Override
    public boolean approveApplication(Integer applicationId, Integer societyId, Integer userId) {
        // 更新申请状态
        boolean isUpdated = applicationMapper.updateApplicationStatus(applicationId, "approved") > 0;
        if (isUpdated) {
            // 添加到成员关系表
            SocietyMember member = new SocietyMember();
            member.setSocietyId(societyId);
            member.setUserId(userId);
            member.setRole("member");
            member.setJoinedAt(LocalDateTime.now());
            return memberMapper.insertSocietyMember(member) > 0;
        }
        return false;
    }

    @Override
    public boolean rejectApplication(Integer applicationId) {
        return applicationMapper.updateApplicationStatus(applicationId, "rejected") > 0;
    }


    @Override
    public List<SocietyApplicationWithUserDTO> getPendingApplicationsWithUserBySocietyId(Integer societyId) {
        // 1. 获取所有待处理的社团申请（不包含用户信息）
        List<SocietyApplicationWithUserDTO> applications = applicationMapper.selectPendingApplicationsBySocietyId(societyId);

        // 2. 遍历申请列表，根据 userId 填充 User 信息
        for (SocietyApplicationWithUserDTO application : applications) {
            // 获取 userId
            Integer userId = application.getUserId();

            // 通过 userId 查询用户信息
            User user = userMapper.findById(userId);

            // 设置 User 信息到 DTO 中
            application.setUser(user);
        }

        return applications;
    }

}
