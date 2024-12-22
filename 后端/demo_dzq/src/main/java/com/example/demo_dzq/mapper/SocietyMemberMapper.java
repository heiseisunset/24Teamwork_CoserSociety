package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.SocietyMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SocietyMemberMapper {

    @Select("SELECT * FROM society_members WHERE society_id = #{societyId}")
    List<SocietyMember> findMembersBySocietyId(Integer societyId);

    @Insert("INSERT INTO society_members (society_id, user_id, role, joined_at) " +
            "VALUES (#{societyId}, #{userId}, #{role}, #{joinedAt})")
    int insertSocietyMember(SocietyMember societyMember);

    // 更新社团成员角色
    @Update("UPDATE society_members SET role = #{role} WHERE society_id = #{societyId} AND user_id = #{userId}")
    int updateMemberRole(@Param("societyId") Integer societyId,
                         @Param("userId") Integer userId,
                         @Param("role") String role);


    // 根据社团ID和用户ID查询社团成员
    @Select("SELECT * FROM society_members WHERE society_id = #{societyId} AND user_id = #{userId}")
    SocietyMember findBySocietyIdAndUserId(@Param("societyId") Integer societyId,
                                           @Param("userId") Integer userId);

}
