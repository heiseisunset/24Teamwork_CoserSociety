package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.SocietyApplicationMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.pojo.SocietyApplication;
import com.example.demo_dzq.pojo.SocietyMember;
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
}
