package org.example.snsproject.service;

import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;

import java.util.List;

public interface ReferralService {

    //查询文章列表（限制年月）
    List<Article> articleList(Integer pageNumber, Integer pageSize, String name, String sort, Integer year, Integer month, Integer tagId, Integer categoryId);


    //获取最热文章
    List<Article> hotArticle();

    //获取最新文章
    List<Article> newArticle();

    //文章归档
    List<Archives> listArchives();

    //获取文章详情
    Article articleDetail(Integer id);

    //发布文章
    Article publishArticle(Article article);
}
