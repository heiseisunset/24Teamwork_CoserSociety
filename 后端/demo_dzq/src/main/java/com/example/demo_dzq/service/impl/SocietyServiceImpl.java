package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.SocietyMapper;
import com.example.demo_dzq.mapper.SocietyMemberMapper;
import com.example.demo_dzq.mapper.UserMapper;
import com.example.demo_dzq.pojo.Society;
import com.example.demo_dzq.pojo.SocietyMember;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.service.SocietyService;
import com.example.demo_dzq.dto.SocietyDetailResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private SocietyMapper societyMapper;

    @Autowired
    private SocietyMemberMapper societyMemberMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Society getSocietyById(Integer societyId) {
        return societyMapper.findSocietyById(societyId);  // 从数据库中查找社团
    }

    @Override
    public List<SocietyMember> getSocietyMembersBySocietyId(Integer societyId) {
        return societyMemberMapper.findMembersBySocietyId(societyId);  // 查找社团成员
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);  // 查找用户
    }

    @Override
    public SocietyDetailResponseDTO getSocietyDetail(Integer societyId) {
        // 获取社团信息
        Society society = getSocietyById(societyId);

        // 获取创始人的名字
        Integer founderId = society.getFounderId();
        User founder = null;
        if (founderId != null) {
            founder = getUserById(founderId);  // 根据founderId查询用户
        }
        String founderName = (founder != null) ? founder.getUsername() : null;  // 如果founder存在，获取用户名

        // 设置社团的创始人名称
        society.setFounderName(founderName);  // 将founderName设置到society对象中

        // 获取社团成员信息
        List<SocietyMember> members = getSocietyMembersBySocietyId(societyId);

        // 创建返回的社团详情
        List<SocietyDetailResponseDTO.SocietyMemberDetail> memberDetails = new ArrayList<>();
        for (SocietyMember member : members) {
            User user = getUserById(member.getUserId());  // 根据用户ID查找用户信息
            String role = member.getRole();  // 获取角色信息
            memberDetails.add(new SocietyDetailResponseDTO.SocietyMemberDetail(user, role));  // 传递角色信息
        }

        return new SocietyDetailResponseDTO(society, memberDetails);
    }

    @Transactional
    @Override
    public boolean createSocietyWithFounderNameAndAddMember(Society society) {
        // 根据创始人名字查询用户表获取创始人ID
        Integer founderId = userMapper.getUserIdByUsername(society.getFounderName());
        if (founderId == null) {
            return false; // 如果未找到对应用户，则创建失败
        }

        // 设置创始人ID和当前时间
        society.setFounderId(founderId);
        society.setCreatedAt(LocalDateTime.now());

        // 插入社团记录
        int societyInsertResult = societyMapper.insertSociety(society);
        if (societyInsertResult <= 0) {
            return false; // 如果插入社团记录失败
        }

        // 获取插入后的社团ID
        Integer societyId = society.getSocietyId();

        // 插入创始人到社团成员表
        SocietyMember societyMember = new SocietyMember();
        societyMember.setSocietyId(societyId);
        societyMember.setUserId(founderId);
        societyMember.setRole("leader");
        societyMember.setJoinedAt(LocalDateTime.now());

        int memberInsertResult = societyMemberMapper.insertSocietyMember(societyMember);
        return memberInsertResult > 0; // 返回是否成功
    }


}
