package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.PhotographyComments;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhotographyCommentsMapper {
    @Select("SELECT * FROM photography_comments WHERE work_id = #{workId}")
    List<PhotographyComments> getCommentsByWorkId(Integer workId);

    @Insert("INSERT INTO photography_comments (work_id, user_id, content, created_at) " +
            "VALUES (#{workId}, #{userId}, #{content}, #{createdAt})")
    int insertPhotographyComment(PhotographyComments comment);

    @Delete("DELETE FROM photography_comments WHERE work_id = #{workId}")
    int deleteCommentsByWorkId(Integer workId);
}

