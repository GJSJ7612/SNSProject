package org.example.snsproject.mapper.activity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.activity.Activity_category;

import java.util.List;

@Mapper
public interface ActivityCategoryMapper {

    @Select("select * from activity_catgory")
    List<Activity_category> getActivityCategory();

    @Select("select * from activity_catgory where id=#{id}")
    Activity_category getActivityCategoryDetaile(Integer id);

    @Select("select activity_catgory.id, avatar, categoryname, count(*) as activities from activity_catgory join activity_brief on activity_catgory.id=activity_brief.category_id  where activity_catgory.id = #{id} " +
            "GROUP BY activity_catgory.id")
    Activity_category getActivityCategoryActivities(Integer id);

    @Select("select activity_catgory.id, avatar, categoryname, count(*) as activities from activity_catgory join activity_brief on activity_catgory.id=activity_brief.category_id " +
            "GROUP BY activity_catgory.id")
    List<Activity_category> getActivityCategoryAllActivities();
}
