package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.OrganizerInfo;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrganizerInfoPostMapper {

    // 插入主办方发布信息
    @Insert("INSERT INTO organizer_info_posts (organizer_id, title, content, main_image_url, detail_images, created_at) " +
            "VALUES (#{organizerId}, #{title}, #{content}, #{mainImageUrl}, #{detailImages}, #{createdAt})")
    void insertOrganizerInfoPost(OrganizerInfoPost organizerInfoPost);

    // 根据主办方ID查询所有发布信息
    @Select("SELECT * FROM organizer_info_posts WHERE organizer_id = #{organizerId}")
    List<OrganizerInfoPost> findPostsByOrganizerId(Integer organizerId);

    // 查询所有主办方发布信息
    @Select("SELECT * FROM organizer_info_posts")
    List<OrganizerInfoPost> findAllOrganizerInfoPosts();

}
