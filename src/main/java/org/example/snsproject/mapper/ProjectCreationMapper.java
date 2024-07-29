package org.example.snsproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.snsproject.entity.Project;

@Mapper
public interface ProjectCreationMapper {

    @Insert("insert into project (uid, category, title, info, deadline, price_lower, price_upper, tel, receiver, create_time) " +
            "values (#{uid}, #{category}, #{title}, #{info}, #{deadline}, #{priceLower}, #{priceUpper}, #{tel}, #{receiver}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "pid") // 自动填充主键ID到实体类中
    int createProject(Project project);
}
