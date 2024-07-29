package org.example.snsproject.mapper.blog;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.blog.Tag;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("select * from tag limit 5")
    List<Tag> hotTag();

    @Select("select id, avatar, tagname, count(*) as articles  from tag join article_tag_link on tag.id=article_tag_link.tag_id " +
            "GROUP BY id")
    List<Tag> tagDetails();

    @Select("select id, avatar, tagname, count(*) as articles  from tag join article_tag_link on tag.id=article_tag_link.tag_id where id=#{id} " +
            "GROUP BY id ")
    Tag tagDetailsById(int id);
}
