package org.example.snsproject.mapper.referral;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.snsproject.entity.blog.Category;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Select("select company.id, avatar, category_name, count(*) as articles from company join article_brief on company.id=article_brief.category_id " +
        "GROUP BY company.id")
    List<Category> getCategory();

    @Select("select company.id, avatar, category_name, count(*) as articles from company join article_brief on company.id=article_brief.category_id  where company.id = #{id} " +
            "GROUP BY company.id")
    Category getCategoryById(int id);

}
