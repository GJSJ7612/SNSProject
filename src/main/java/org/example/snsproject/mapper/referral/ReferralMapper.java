package org.example.snsproject.mapper.referral;

import org.apache.ibatis.annotations.*;
import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Article_body;

import java.util.List;

@Mapper
public interface ReferralMapper {

    List<Article> articleList(@Param("name") String name, @Param("sort") String sort, int year, int month, int tagId, int categoryId, Integer userStatus);

    @Select("select id, title from referral_brief order by view_counts desc limit 5")
    List<Article> hotArticle();

    @Select("select id, title from referral_brief order by createDate desc limit 5")
    List<Article> newArticle();

    @Select("select YEAR(createDate) AS year, MONTH(createDate) AS month, COUNT(*) AS count, SUM(comment_counts) AS commentCounts from referral_brief " +
            "where status=1 GROUP BY YEAR(createDate), MONTH(createDate) ORDER BY year, month")
    List<Archives> listArchives();

    Article articleDetail(Integer id);

    @Insert("insert into referral_body(content, content_html) VALUES (#{content}, #{contentHtml})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBody(Article_body body);

    @Insert("insert into referral_tech_link(article_id, tag_id) VALUES (#{articleId}, #{tagId})")
    void insertArticleTag(int articleId, int tagId);

    @Insert("insert into referral_brief(createDate, summary, title, author_id, body_id, category_id) " +
            "values (#{createDate}, #{summary}, #{title}, #{authorId}, #{bodyId}, #{categoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertArticle(Article article);

    @Update("update referral_brief set view_counts=#{viewCount} where id=#{id}")
    void updateView(Integer id, Integer viewCount);

    @Update("update referral_brief set comment_counts=#{commentCount} where id=#{id}")
    void updateComment(Integer id, Integer commentCount);

    @Update("update referral_brief set status=1 where id=#{id}")
    void articleAgree(Integer id);

    @Update("update referral_brief set status=2 where id=#{id}")
    void articleReject(Integer id);
}
