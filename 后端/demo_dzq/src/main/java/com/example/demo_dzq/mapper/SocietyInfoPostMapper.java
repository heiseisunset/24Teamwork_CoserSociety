package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.SocietyInfoPost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface SocietyInfoPostMapper {

    // 插入社团信息发布记录
    @Insert("INSERT INTO society_info_posts (society_id, title, content, main_image_url, detail_images, created_at) " +
            "VALUES (#{societyId}, #{title}, #{content}, #{mainImageUrl}, #{detailImages}, NOW())")
    int insertSocietyInfoPost(SocietyInfoPost post);
}
