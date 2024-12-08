package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.Society;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocietyMapper {

    // 查询所有社团信息
    @Select("SELECT * FROM societies")
    List<Society> findAllSocieties();

    @Select("SELECT * FROM societies WHERE society_id = #{societyId}")
    Society findSocietyById(Integer societyId);
}
