package org.example.snsproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.snsproject.entity.Project;

@Mapper
public interface ProjectCreationMapper {

    @Insert("insert into project (uid, category, title, info, deadline, price_lower, price_upper, tel, receiver, create_time) " +
            "values (#{uid}, #{category}, #{title}, #{info}, #{deadline}, #{priceLower}, #{priceUpper}, #{tel}, #{receiver}, #{createTime})")
    void createProject(Project project);
}
