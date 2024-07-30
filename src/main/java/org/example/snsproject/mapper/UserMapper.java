package org.example.snsproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.snsproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserMapper {

    @Select("select * from user where account = #{username}")
    User findUserByUsername(String username);

    @Insert("INSERT into user (account, password, nickname, create_time, update_time) " +
            "VALUES(#{account}, #{password}, #{nickname}, #{createTime}, #{updateTime})")
    @Transactional
    void register(User user);

    @Update("update user set nickname=#{nickname}, sex=#{sex}, birth=#{birth}, email=#{email}, phone=#{phone}," +
            "address=#{address}, position=#{position}, avatar=#{avatar} where id=#{id}")
    void updateUserDetail(User user);
}
