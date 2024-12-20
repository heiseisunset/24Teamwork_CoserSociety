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

//    // 根据作品ID获取所有评论
//    @Select("SELECT * FROM comments WHERE work_id = #{workId}")
//    List<Comment> findCommentsByWorkId(Integer workId);

    // 根据作品ID获取所有评论和用户信息
    @Select("""
        SELECT c.*, u.user_id AS userId, u.username AS username, u.email AS email, 
               u.phone AS phone, u.avatar_url AS avatarUrl, u.bio AS bio, u.role AS role
        FROM comments c
        JOIN user u ON c.user_id = u.user_id
        WHERE c.work_id = #{workId}
    """)
    @Results({
            @Result(property = "id", column = "comment_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "workId", column = "work_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "created_at"),
            @Result(property = "user.userId", column = "user_id"),
            @Result(property = "user.username", column = "username"),
            @Result(property = "user.email", column = "email"),
            @Result(property = "user.phone", column = "phone"),
            @Result(property = "user.avatarUrl", column = "avatar_url"),
            @Result(property = "user.bio", column = "bio"),
            @Result(property = "user.role", column = "role")
    })
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
