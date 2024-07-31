package org.example.snsproject.mapper.activity;
import org.apache.ibatis.annotations.*;
import org.example.snsproject.entity.User;
import org.example.snsproject.entity.activity.*;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Tag;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ActivityMapper {

    List<Activity> activitiesFinished(Integer tagId, Integer categoryId, LocalDateTime currentDate);

    List<Activity> activitiesUnFinished(Integer tagId, Integer categoryId, LocalDateTime currentDate);

    @Select("select id, title from activity_brief order by view_counts desc limit 5")
    List<Activity> hotActivities();

    @Select("select id, title from activity_brief order by createDate desc limit 5")
    List<Activity> newActivities();

    @Update("update activity_brief set view_counts=view_counts+1 where id=#{id}")
    void updateViewActivities(Integer id);

    @Select("select * from activity_brief join user on activity_brief.author_id = user.id " +
            "join activity_body on activity_brief.body_id = activity_body.id " +
            "join activity_catgory on activity_brief.category_id = activity_catgory.id where activity_brief.id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "commentCounts", column = "comment_counts"),
            @Result(property = "createDate", column = "createDate"),
            @Result(property = "summary", column = "summary"),
            @Result(property = "title", column = "title"),
            @Result(property = "viewCounts", column = "view_counts"),
            @Result(property = "weight", column = "weight"),
            @Result(property = "author", column = "author_id", one = @One(select = "selectAuthor")),
            @Result(property = "tags", column = "id", one = @One(select = "selectTags")),
            @Result(property = "body", column = "body_id", one = @One(select = "selectBody")),
            @Result(property = "category", column = "category_id", one = @One(select = "selectCategory")),
    })
    Activity viewActivities(Integer id);

    @Select("SELECT id, content FROM activity_body WHERE id = #{bodyId}")
    Activity_body selectBody(@Param("bodyId") int bodyId);

    @Select("SELECT avatar, categoryname, description, id FROM activity_catgory WHERE id = #{categoryId}")
    Activity_category selectCategory(@Param("categoryId") int categoryId);

    @Insert("insert into activity_body(content, content_html) values (#{content}, #{contentHtml})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertBody(Activity_body body);

    @Insert("insert into activity_brief(createDate, summary, title, weight, start_date, end_date, author_id, body_id, category_id) " +
            "values (#{createDate}, #{summary}, #{title}, #{weight}, #{startDate}, #{endDate}, #{authorId}, #{bodyId}, #{categoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertArticle(Activity activity);

    @Insert("insert into activity_tag_link(activity_id, tag_id) VALUES (#{activityId}, #{tagId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertArticleTag(int activityId, int tagId);

    @Select("select YEAR(start_date) AS year, MONTH(start_date) AS month, COUNT(*) AS count, SUM(comment_counts) AS commentCounts from activity_brief " +
            "where end_date < now() GROUP BY YEAR(start_date), MONTH(start_date) ORDER BY year, month")
    List<Activity_archives> listArchivesActivities();

    @Select("select * from activity_brief where author_id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "commentCounts", column = "comment_counts"),
            @Result(property = "createDate", column = "createDate"),
            @Result(property = "summary", column = "summary"),
            @Result(property = "title", column = "title"),
            @Result(property = "viewCounts", column = "view_counts"),
            @Result(property = "weight", column = "weight"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "author", column = "author_id", one = @One(select = "selectAuthor")),
            @Result(property = "tags", column = "id", one = @One(select = "selectTags"))
    })
    List<Activity> getActivityUser(int id);

    @Update("update activity_brief set view_counts=view_counts+1 where id=#{id}")
    void updateComment(int id);

    @Select("SELECT id, nickname ,email FROM user WHERE id = #{userId}")
    User selectAuthor(@Param("userId") int userId);

    @Select("SELECT distinct tagname FROM activity_tag_link join tag on activity_tag_link.tag_id = tag.id WHERE activity_tag_link.activity_id= #{activityId}")
    List<Tag> selectTags(@Param("activityId") int activityId);
}
