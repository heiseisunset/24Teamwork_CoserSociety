package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.SocietyInfoPost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocietyInfoPostMapper {

    // 插入社团信息发布记录
    @Insert("INSERT INTO society_info_posts (society_id, title, content, main_image_url, detail_images, created_at) " +
            "VALUES (#{societyId}, #{title}, #{content}, #{mainImageUrl}, #{detailImages}, NOW())")
    int insertSocietyInfoPost(SocietyInfoPost post);

    // 根据社团ID查询社团发布的所有信息
    @Select("SELECT * FROM society_info_posts WHERE society_id = #{societyId}")
    List<SocietyInfoPost> getSocietyInfoPostsBySocietyId(Integer societyId);
}
