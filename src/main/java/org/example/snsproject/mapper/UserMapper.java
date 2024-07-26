package org.example.snsproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserMapper {

    @Select("select * from user where account = #{username}")
    User findUserByUsername(String username);

    @Insert("INSERT into user ( account, password, nickname, status, create_time, update_time) " +
            "VALUES(#{account}, #{password}, #{nickname}, #{status}, #{createTime}, #{updateTime})")
    @Transactional
    void register(User user);
}
