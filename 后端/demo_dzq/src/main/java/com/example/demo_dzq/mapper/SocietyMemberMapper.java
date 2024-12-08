package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.SocietyMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocietyMemberMapper {

    @Select("SELECT * FROM society_members WHERE society_id = #{societyId}")
    List<SocietyMember> findMembersBySocietyId(Integer societyId);
}
