package org.example.snsproject.mapper.activity;

import org.apache.ibatis.annotations.*;
import org.example.snsproject.entity.User;
import org.example.snsproject.entity.activity.Activity_comment;

import java.util.List;

@Mapper
public interface ActivityCommentMapper {

    @Select("select * from activity_comment join user on activity_comment.author_id = user.id where parent_id IS NULL and activity_id=#{id} order by create_date desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "level", column = "level"),
            @Result(property = "author", column = "author_id", one = @One(select = "selectAuthor"))
    })
    List<Activity_comment> getTopComments(int id);

    @Select("SELECT id, avatar, nickname FROM user WHERE id = #{userId}")
    User selectAuthor(@Param("userId") int userId);

    @Select("select * from activity_comment join user on activity_comment.author_id = user.id where parent_id=#{parentId} order by create_date")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "level", column = "level"),
            @Result(property = "author", column = "author_id", one = @One(select = "selectAuthor")),
            @Result(property = "toUser", column = "to_user", one = @One(select = "selectAuthor"))
    })
    List<Activity_comment> getComments(int id);

    @Insert("INSERT into activity_comment(content, create_date, activity_id, author_id, level) " +
            "values(#{content}, #{createDate}, #{activityId}, #{authorId}, #{level})")
    void createCommentNoPT(Activity_comment activityComment);


    @Insert("INSERT into activity_comment(content, create_date, activity_id, author_id, level) " +
            "values(#{content}, #{createDate}, #{activityId}, #{authorId}, #{level})")
    void createComment(Activity_comment activityComment);
}
