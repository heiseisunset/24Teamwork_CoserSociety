package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.Comment;
import com.example.demo_dzq.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 插入评论
    @Insert("INSERT INTO comments (work_id, user_id, content, created_at) " +
            "VALUES (#{workId}, #{userId}, #{content}, #{createTime})")
    void insertComment(Comment comment);

    // 根据作品ID获取所有评论
    @Select("SELECT * FROM comments WHERE work_id = #{workId}")
    List<Comment> findCommentsByWorkId(Integer workId);

    // 删除评论
    @Delete("DELETE FROM comments WHERE comment_id = #{commentId}")
    int deleteComment(Integer commentId);

    // 根据评论ID查询评论
    @Select("SELECT * FROM comments WHERE comment_id = #{commentId}")
    Comment findCommentById(Integer commentId);

    // 根据作品ID删除所有相关评论
    @Delete("DELETE FROM comments WHERE work_id = #{workId}")
    void deleteCommentsByWorkId(Integer workId);

    // 根据 userId 查询用户信息
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User findUserById(Integer userId);
}
