package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.CoserWork;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CoserWorkMapper {

    // 插入 Coser 作品
    @Insert("INSERT INTO coser_works (user_id, title, original_work, character_name, content, image_url, publish_date, read_count, photographer_id, makeup_artist_id, post_production_id, created_at, category) " +
            "VALUES (#{userId}, #{title}, #{originalWork}, #{characterName}, #{content}, #{imageUrl}, #{publishDate}, #{readCount}, #{photographerId}, #{makeupArtistId}, #{postProductionId}, #{createdAt}, #{category})")
    void insertCoserWork(CoserWork coserWork);


    @Select("SELECT * FROM coser_works")
    List<CoserWork> selectAllCoserWorks();

    // 根据用户ID查询所有Coser作品
    @Select("SELECT * FROM coser_works WHERE user_id = #{userId}")
    List<CoserWork> findCoserWorksByUserId(Integer userId);

    // 删除Coser作品
    @Delete("DELETE FROM coser_works WHERE work_id = #{workId}")
    int deleteCoserWork(Integer workId);

    @Select("SELECT * FROM coser_works WHERE work_id = #{workId}")
    CoserWork getCoserWorkById(Integer workId);
}
