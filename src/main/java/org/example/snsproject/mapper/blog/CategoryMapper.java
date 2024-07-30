package org.example.snsproject.mapper.blog;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.blog.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select category.id, avatar, category_name, count(*) as articles from category join article_brief on category.id=article_brief.category_id " +
            "GROUP BY category.id")
    List<Category> getCategory();

    @Select("select category.id, avatar, category_name, count(*) as articles from category join article_brief on category.id=article_brief.category_id  where category.id = #{id} " +
            "GROUP BY category.id")
    Category getCategoryById(int id);
}
