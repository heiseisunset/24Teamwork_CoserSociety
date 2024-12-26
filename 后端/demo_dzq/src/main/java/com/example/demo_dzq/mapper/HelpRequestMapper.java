package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.HelpRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HelpRequestMapper {

    @Insert("INSERT INTO help_requests (user_id, title, event_date, duration, image_url, description, phone, contact, fee, city, created_at, is_resolved) " +
            "VALUES (#{userId}, #{title}, #{eventDate}, #{duration}, #{imageUrl}, #{description}, #{phone}, #{contact}, #{fee}, #{city}, NOW(), FALSE)")
    @Options(useGeneratedKeys = true, keyProperty = "requestId")
    int insertHelpRequest(HelpRequest helpRequest);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();
    @Select("SELECT * FROM help_requests")
    List<HelpRequest> selectAllHelpRequests();

    // 获取指定用户发布的所有求助请求
    @Select("SELECT * FROM help_requests WHERE user_id = #{userId}")
    List<HelpRequest> selectHelpRequestsByUserId(@Param("userId") Integer userId);

    @Update("UPDATE help_requests SET is_resolved = TRUE WHERE request_id = #{requestId}")
    int markAsResolved(@Param("requestId") Integer requestId);

    @Update("UPDATE help_requests SET is_resolved = FALSE WHERE request_id = #{requestId}")
    int markAsUnresolved(@Param("requestId") Integer requestId);

}
