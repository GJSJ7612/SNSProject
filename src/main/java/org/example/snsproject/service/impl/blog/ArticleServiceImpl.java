package org.example.snsproject.service.impl.blog;

import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.blog.ArticleMapper;
import org.example.snsproject.service.ArticleService;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> articleList(Integer pageNumber, Integer pageSize, String name, String sort, Integer year, Integer month, Integer tagId, Integer categoryId) {
        //PageBean<Article_brief> pageBean = new PageBean<>();
        //PageHelper.startPage(pageNumber, pageSize);
        name = name.substring(2);
        List<Article> article_briefs = articleMapper.articleList(name, sort, year, month, tagId, categoryId);
        /*Page<Article_brief> page = (Page<Article_brief>) article_briefs; // 强制类型转换
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());*/

        return article_briefs;
    }

    @Override
    public List<Article> hotArticle() {
        return articleMapper.hotArticle();
    }

    @Override
    public List<Article> newArticle() {
        return articleMapper.newArticle();
    }

    @Override
    public List<Archives> listArchives() {
        return articleMapper.listArchives();
    }

    @Override
    public Article articleDetail(Integer id) {
        Article article = articleMapper.articleDetail(id);
        article.setViewCounts(article.getViewCounts()+1);
        articleMapper.updateView(id, article.getViewCounts());
        return article;
    }

    @Override
    public Article publishArticle(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        article.setAuthorId(id);
        article.setCreateDate(LocalDateTime.now());
        articleMapper.insertBody(article.getBody());
        article.setBodyId(article.getBody().getId());
        article.setCategoryId(article.getCategory().getId());
        articleMapper.insertArticle(article);
        for(Tag tag : article.getTags()) {
            articleMapper.insertArticleTag(article.getId(), tag.getId());
        }
        article.setArticleId(article.getId());
        return article;
    }
}
