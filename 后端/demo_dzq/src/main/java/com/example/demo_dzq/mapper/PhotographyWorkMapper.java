package com.example.demo_dzq.mapper;

import com.example.demo_dzq.pojo.PhotographyWork;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographyWorkMapper {

    @Insert("INSERT INTO photography_works (user_id, title, original_work, character_name, content, image_url, publish_date, created_at, read_count, category) " +
            "VALUES (#{userId}, #{title}, #{originalWork}, #{characterName}, #{content}, #{imageUrl}, #{publishDate}, #{createdAt}, #{readCount}, #{category})")
    void insertPhotographyWork(PhotographyWork photographyWork);




    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId(); // 获取插入的最新ID

    @Select("SELECT * FROM photography_works")
    List<PhotographyWork> selectAllPhotographyWorks();

    // 查询指定用户ID的所有摄影作品
    @Select("SELECT * FROM photography_works WHERE user_id = #{userId}")
    List<PhotographyWork> selectPhotographyWorksByUserId(Integer userId);

    // 删除指定workId的摄影作品
    @Delete("DELETE FROM photography_works WHERE work_id = #{workId}")
    int deletePhotographyWork(Integer workId);

    @Select("SELECT * FROM photography_works WHERE work_id = #{workId}")
    PhotographyWork getPhotographyWorkById(Integer workId);
}
