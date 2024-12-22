package com.example.demo_dzq.mapper;

import com.example.demo_dzq.dto.SocietyMemberWithUserDTO;
import com.example.demo_dzq.pojo.SocietyMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SocietyMemberMapper {

    // 查询指定社团ID的所有成员（不包含用户详细信息）
    @Select("SELECT * FROM society_members WHERE society_id = #{societyId}")
    List<SocietyMember> findMembersBySocietyId(Integer societyId);

    // 向社团成员表插入一条新记录
    @Insert("INSERT INTO society_members (society_id, user_id, role, joined_at) " +
            "VALUES (#{societyId}, #{userId}, #{role}, #{joinedAt})")
    int insertSocietyMember(SocietyMember societyMember);

    // 更新指定社团成员的角色
    @Update("UPDATE society_members SET role = #{role} WHERE society_id = #{societyId} AND user_id = #{userId}")
    int updateMemberRole(@Param("societyId") Integer societyId,
                         @Param("userId") Integer userId,
                         @Param("role") String role);

    // 根据社团ID和用户ID查询社团成员
    @Select("SELECT * FROM society_members WHERE society_id = #{societyId} AND user_id = #{userId}")
    SocietyMember findBySocietyIdAndUserId(@Param("societyId") Integer societyId,
                                           @Param("userId") Integer userId);

    // 查询指定社团ID的所有成员（不包含用户详细信息）
    @Select("SELECT * FROM society_members WHERE society_id = #{societyId}")
    List<SocietyMember> selectMembersBySocietyId(@Param("societyId") Integer societyId);

    // 根据社团ID和用户ID获取成员信息
    @Select("SELECT * FROM society_members WHERE society_id = #{societyId} AND user_id = #{userId}")
    SocietyMember selectMemberBySocietyIdAndUserId(@Param("societyId") Integer societyId,
                                                   @Param("userId") Integer userId);

    // 查询指定社团ID的所有成员，并包含用户详细信息（使用JOIN关联user表）
    @Select("SELECT sm.member_id, sm.society_id, sm.user_id, sm.role, sm.joined_at, u.* " +
            "FROM society_members sm " +
            "JOIN user u ON sm.user_id = u.user_id " +
            "WHERE sm.society_id = #{societyId}")
    List<SocietyMemberWithUserDTO> selectMembersWithUserBySocietyId(@Param("societyId") Integer societyId);
}
