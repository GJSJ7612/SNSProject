package org.example.snsproject.mapper.activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.activity.Activity_tag;
import org.example.snsproject.entity.blog.Tag;

import java.util.List;

@Mapper
public interface ActivityTagMapper {
    @Select("select * from activity_tag")
    List<Activity_tag> getActivityTags();

    @Select("select * from activity_tag where id=#{id}")
    Activity_tag getActivityTagsById(int id);

    @Select("select activity_tag.id, avatar, tagname, count(*) as activities from activity_tag join activity_tag_link on activity_tag.id=activity_tag_link.tag_id " +
            "GROUP BY activity_tag.id")
    List<Activity_tag> etActivityTagsDetail();

    @Select("select * from activity_tag limit 5")
    List<Activity_tag> getHotActivityTags();

    @Select("select activity_tag.id, avatar, tagname, count(*) as activities from activity_tag join activity_tag_link on activity_tag.id=activity_tag_link.tag_id where id=#{id} " +
            "GROUP BY activity_tag.id")
    Activity_tag getActivityTagsDetailbyId(Integer id);
}
