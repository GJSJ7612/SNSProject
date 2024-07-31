package org.example.snsproject.service.impl.blog;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.User;
import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.blog.ArticleMapper;
import org.example.snsproject.service.ArticleService;
import org.example.snsproject.service.UserService;
import org.example.snsproject.utils.JwtUtil;
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
    @Autowired
    private UserService userService;

    @Override
    public List<Article> articleList(Integer pageNumber, Integer pageSize, String name, String sort, Integer year, Integer month, Integer tagId, Integer categoryId, String token) {
        //PageBean<Article_brief> pageBean = new PageBean<>();
        //PageHelper.startPage(pageNumber, pageSize);
        name = name.substring(2);
        List<Article> article_briefs;
        if( token == null || token.equals("undefined")){
            article_briefs = articleMapper.articleList(name, sort, year, month, tagId, categoryId, 0);
        }else{
            User user = userService.findUserByAccount((String) JwtUtil.parseToken(token).get("account"));
            article_briefs = articleMapper.articleList(name, sort, year, month, tagId, categoryId, user.getAdminStatus());
        }
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

    @Override
    public void articleAgree(Integer id) {
        articleMapper.articleAgree(id);
    }

    @Override
    public void articleReject(Integer id) {
        articleMapper.articleReject(id);
    }

    @Override
    public List<Article> mineArticles() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        return articleMapper.mineArticles(id);
    }

    @Override
    public void deleteArticle(Integer id) {
        Article article = articleMapper.articleDetail(id);
        articleMapper.deleteArticleTags(id);
        articleMapper.deleteArticle(id);
        articleMapper.deleteArticleBody(article.getBody().getId());

    }
}
