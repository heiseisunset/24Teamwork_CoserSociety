package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.SocietyMember;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocietyMemberMapper {

    @Select("SELECT * FROM society_members WHERE society_id = #{societyId}")
    List<SocietyMember> findMembersBySocietyId(Integer societyId);

    @Insert("INSERT INTO society_members (society_id, user_id, role, joined_at) " +
            "VALUES (#{societyId}, #{userId}, #{role}, #{joinedAt})")
    int insertSocietyMember(SocietyMember societyMember);
}
