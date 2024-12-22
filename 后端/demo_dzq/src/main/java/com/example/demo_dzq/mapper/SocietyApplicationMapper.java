package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.SocietyApplication;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SocietyApplicationMapper {

    @Insert("INSERT INTO society_applications (society_id, user_id, status) " +
            "VALUES (#{societyId}, #{userId}, #{status})")
    int insertApplication(SocietyApplication application);

    @Select("SELECT * FROM society_applications WHERE society_id = #{societyId} AND status = 'pending'")
    List<SocietyApplication> findPendingApplicationsBySocietyId(@Param("societyId") Integer societyId);

    @Update("UPDATE society_applications SET status = #{status} WHERE application_id = #{applicationId}")
    int updateApplicationStatus(@Param("applicationId") Integer applicationId, @Param("status") String status);

    @Delete("DELETE FROM society_applications WHERE application_id = #{applicationId}")
    int deleteApplication(@Param("applicationId") Integer applicationId);
}
