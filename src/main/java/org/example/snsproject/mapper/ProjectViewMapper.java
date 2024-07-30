package org.example.snsproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.UserProject;

import java.util.List;

@Mapper
public interface ProjectViewMapper {
    @Select("select project.*, user.* from project left join user on project.uid = user.id")
    @Results({
            @Result(property = "priceLower", column = "price_lower"),
            @Result(property = "priceUpper", column = "price_upper")
    })
    List<UserProject> brief();

    @Select("select project.*, user.* from project join user on project.uid = user.id " +
            "join application on project.pid = application.pid where application.uid = #{id}")
    @Results({
            @Result(property = "priceLower", column = "price_lower"),
            @Result(property = "priceUpper", column = "price_upper")
    })
    List<UserProject> applied(int id);

    @Select("select project.*, user.* from project left join user on project.uid = user.id where uid = #{id}")
    @Results({
            @Result(property = "priceLower", column = "price_lower"),
            @Result(property = "priceUpper", column = "price_upper")
    })
    List<UserProject> created(int id);

    @Select("select project.*, user.* from project left join user on project.uid = user.id where receiver = #{id}")
    @Results({
            @Result(property = "priceLower", column = "price_lower"),
            @Result(property = "priceUpper", column = "price_upper")
    })
    List<UserProject> received(int id);
}
