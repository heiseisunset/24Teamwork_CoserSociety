package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.OrganizerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrganizerInfoMapper {

    // 插入主办方信息
    @Insert("INSERT INTO organizer_info (user_id, name, headquarters_city, description, logo_url, created_at) " +
            "VALUES (#{userId}, #{name}, #{headquartersCity}, #{description}, #{logoUrl}, #{createdAt})")
    void insertOrganizerInfo(OrganizerInfo organizerInfo);

    // 根据 user_id 查询主办方信息
    @Select("SELECT * FROM organizer_info WHERE user_id = #{userId}")
    OrganizerInfo findByUserId(int userId);

    // 更新主办方信息
    @Update("UPDATE organizer_info SET name = #{name}, headquarters_city = #{headquartersCity}, description = #{description}, " +
            "logo_url = #{logoUrl} WHERE user_id = #{userId}")
    void updateOrganizerInfo(OrganizerInfo organizerInfo);

    @Select("SELECT * FROM organizer_info")
    List<OrganizerInfo> selectAllOrganizerInfos();
}
