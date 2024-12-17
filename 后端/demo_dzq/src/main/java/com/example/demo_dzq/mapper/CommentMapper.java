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
    @Select("SELECT comment_id AS id, user_id AS userId, work_id AS workId, content, created_at AS createTime " +
            "FROM comments WHERE work_id = #{workId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "workId", column = "workId"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Comment> findCommentsByWorkId(Integer workId);

    // 删除评论
    @Delete("DELETE FROM comments WHERE comment_id = #{commentId}")
    int deleteComment(Integer commentId);

    // 根据评论ID查询评论
    @Select("SELECT comment_id AS id, user_id AS userId, work_id AS workId, content, created_at AS createTime " +
            "FROM comments WHERE comment_id = #{commentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "workId", column = "workId"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "createTime")
    })
    Comment findCommentById(Integer commentId);

    // 根据作品ID删除所有相关评论
    @Delete("DELETE FROM comments WHERE work_id = #{workId}")
    void deleteCommentsByWorkId(Integer workId);

    // 根据 userId 查询用户信息
    @Select("SELECT user_id AS userId, username, password, email, phone, avatar_url AS avatarUrl, bio, role, created_at AS createdAt " +
            "FROM user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "userId"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "avatarUrl", column = "avatarUrl"),
            @Result(property = "bio", column = "bio"),
            @Result(property = "role", column = "role"),
            @Result(property = "createdAt", column = "createdAt")
    })
    User findUserById(Integer userId);
}
