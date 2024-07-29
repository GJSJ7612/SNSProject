package org.example.snsproject.mapper.referral;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.blog.Tag;

import java.util.List;

@Mapper
public interface TechMapper {

    @Select("select * from tech limit 5")
    List<Tag> hotTag();

    @Select("select id, avatar, tagname, count(*) as articles  from tech join article_tag_link on tech.id=article_tag_link.tag_id " +
            "GROUP BY id")
    List<Tag> tagDetails();

    @Select("select id, avatar, tagname, count(*) as articles  from tech join article_tag_link on tech.id=article_tag_link.tag_id where id=#{id} " +
            "GROUP BY id ")
    Tag tagDetailsById(int id);
}
