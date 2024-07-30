package org.example.snsproject.mapper.referral;

import org.apache.ibatis.annotations.*;
import org.example.snsproject.entity.User;
import org.example.snsproject.entity.blog.Comment;

import java.util.List;

@Mapper
public interface CommentReferralMapper {

    @Select("select * from comment_referral join user on comment_referral.author_id = user.id where parent_id IS NULL and article_id=#{id} order by create_date desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "level", column = "level"),
            @Result(property = "author", column = "author_id", one = @One(select = "selectAuthor"))
    })
    List<Comment> getTopComments(Integer id);

    @Select("select * from comment_referral join user on comment_referral.author_id = user.id where parent_id=#{parentId} order by create_date")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "level", column = "level"),
            @Result(property = "author", column = "author_id", one = @One(select = "selectAuthor")),
            @Result(property = "toUser", column = "to_user", one = @One(select = "selectAuthor"))
    })
    List<Comment> getComments(Integer parentId);

    @Select("SELECT id, avatar, nickname FROM user WHERE id = #{userId}")
    User selectAuthor(@Param("userId") int userId);


    @Insert("INSERT into comment_referral(content, create_date, article_id, author_id, parent_id, to_user, level) " +
            "values(#{content}, #{createDate}, #{articleId}, #{authorId}, #{parentId}, #{toUid}, #{level})")
    void createComment(Comment comment);

    @Insert("INSERT into comment_referral(content, create_date, article_id, author_id, level) " +
            "values(#{content}, #{createDate}, #{articleId}, #{authorId}, #{level})")
    void createCommentNoPT(Comment comment);
}
