package com.example.demo_dzq.mapper;

import com.example.demo_dzq.dto.SocietyApplicationWithUserDTO;
import com.example.demo_dzq.pojo.SocietyApplication;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SocietyApplicationMapper {

    // 插入一条新的申请记录
    @Insert("INSERT INTO society_applications (society_id, user_id, status) " +
            "VALUES (#{societyId}, #{userId}, #{status})")
    int insertApplication(SocietyApplication application);

    // 查询指定社团ID且状态为 'pending' 的所有申请
    @Select("SELECT * FROM society_applications WHERE society_id = #{societyId} AND status = 'pending'")
    List<SocietyApplication> findPendingApplicationsBySocietyId(@Param("societyId") Integer societyId);

    // 更新指定申请的状态
    @Update("UPDATE society_applications SET status = #{status} WHERE application_id = #{applicationId}")
    int updateApplicationStatus(@Param("applicationId") Integer applicationId, @Param("status") String status);

    // 删除指定申请记录
    @Delete("DELETE FROM society_applications WHERE application_id = #{applicationId}")
    int deleteApplication(@Param("applicationId") Integer applicationId);

    // 根据社团ID获取所有状态为 'pending' 的申请记录
    @Select("SELECT * FROM society_applications WHERE society_id = #{societyId} AND status = 'pending'")
    List<SocietyApplication> selectApplicationsBySocietyId(Integer societyId);

    // 根据社团ID和用户ID获取指定的申请记录
    @Select("SELECT * FROM society_applications WHERE society_id = #{societyId} AND user_id = #{userId}")
    SocietyApplication selectApplicationBySocietyIdAndUserId(@Param("societyId") Integer societyId,
                                                             @Param("userId") Integer userId);

//    // 查询指定社团ID且状态为 'pending' 的申请，并包含用户详细信息
//    @Select("SELECT sa.application_id, sa.society_id, sa.user_id, sa.status, sa.created_at, sa.updated_at, u.* " +
//            "FROM society_applications sa " +
//            "JOIN user u ON sa.user_id = u.user_id " +
//            "WHERE sa.society_id = #{societyId} AND sa.status = 'pending'")
//    List<SocietyApplicationWithUserDTO> selectPendingApplicationsWithUserBySocietyId(@Param("societyId") Integer societyId);

    @Select("SELECT sa.application_id, sa.society_id, sa.user_id, sa.status, sa.created_at, sa.updated_at, " +
            "u.user_id AS user_id, u.username, u.email, u.avatar_url, u.created_at AS user_created_at " +
            "FROM society_applications sa " +
            "JOIN user u ON sa.user_id = u.user_id " +
            "WHERE sa.society_id = #{societyId} AND sa.status = 'pending'")
    List<SocietyApplicationWithUserDTO> selectPendingApplicationsBySocietyId(@Param("societyId") Integer societyId);
}


