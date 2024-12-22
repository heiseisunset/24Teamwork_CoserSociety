package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.Society;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocietyMapper {

    // 查询所有社团信息
    @Select("SELECT * FROM societies")
    List<Society> findAllSocieties();

    @Select("SELECT * FROM societies WHERE society_id = #{societyId}")
    Society findSocietyById(Integer societyId);

    @Insert("INSERT INTO societies (name, founder_id, main_city, description, logo_url, created_at) " +
            "VALUES (#{name}, #{founderId},  #{mainCity}, #{description}, #{logoUrl}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "societyId") // 获取自动生成的主键
    int insertSociety(Society society);
}
