package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.CommentForOrganizerInfoPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentForOrganizerInfoPostMapper {

    @Select("SELECT * FROM comments_for_organizer_info_posts WHERE post_id = #{postId}")
    List<CommentForOrganizerInfoPost> findByPostId(Integer postId);
}
