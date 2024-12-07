package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    // 查询用户名是否存在
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    // 根据用户ID查找用户
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User findById(int userId);

    // 根据用户邮箱查找用户
    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    // 根据用户手机号查找用户
    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findByPhone(String phone);

    // 插入新用户
    @Insert("INSERT INTO user (username, password, email, phone, avatar_url, bio, role, created_at) " +
            "VALUES (#{username}, #{password}, #{email}, #{phone}, #{avatarUrl}, #{bio}, #{role}, #{createdAt})")
    void insertUser(User user);

    // 更新用户信息
    @Update("UPDATE user SET username = #{username}, role = #{role} WHERE user_id = #{userId}")
    void updateUser(User user);

    // 更新用户密码
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{userId}")
    void updatePassword(User user);

    // 根据用户名查询用户ID
    @Select("SELECT user_id FROM user WHERE username = #{username}")
    Integer getUserIdByUsername(String username);

    // 查询角色为 "organizer" 的所有用户
    @Select("SELECT * FROM user WHERE role = 'organizer'")
    List<User> findOrganizers();

    // 根据用户ID查询用户信息
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User getUserById(Integer userId);
}
