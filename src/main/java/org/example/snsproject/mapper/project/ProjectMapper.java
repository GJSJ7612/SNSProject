package org.example.snsproject.mapper.project;

import org.apache.ibatis.annotations.*;
import org.example.snsproject.entity.project.Application;
import org.example.snsproject.entity.project.UserProjectDetail;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("select project.*, user.* from project left join user on project.uid = user.id where project.pid = #{pid}")
    @Results({
            @Result(property = "priceLower", column = "price_lower"),
            @Result(property = "priceUpper", column = "price_upper")
    })
    UserProjectDetail detail(long pid);

    @Select("select application.*, user.* from application left join user on application.uid = user.id where pid = #{pid}")
    @Results({
            @Result(property = "id", column = "application.id")
    })
    List<Application> applicants(long pid);

    @Insert("insert into application (uid, pid, bid, time, resume) values (#{uid}, #{pid}, #{bid}, #{time}, #{resume})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int apply(Application application);

    @Update("update project set receiver = #{uid} where pid = #{pid}")
    void choose(long pid, int uid);
}
