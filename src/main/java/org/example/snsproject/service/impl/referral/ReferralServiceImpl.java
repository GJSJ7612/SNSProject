package org.example.snsproject.service.impl.referral;

import org.example.snsproject.entity.User;
import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.referral.ReferralMapper;
import org.example.snsproject.service.ReferralService;
import org.example.snsproject.service.UserService;
import org.example.snsproject.utils.JwtUtil;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    private ReferralMapper referralMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<Article> articleList(Integer pageNumber, Integer pageSize, String name, String sort, Integer year, Integer month, Integer tagId, Integer categoryId, String token) {
        //PageBean<Article_brief> pageBean = new PageBean<>();
        //PageHelper.startPage(pageNumber, pageSize);
        name = name.substring(2);
        List<Article> article_briefs;
        if( token == null || token.equals("undefined")){
            article_briefs = referralMapper.articleList(name, sort, year, month, tagId, categoryId, 0);
        }else{
            User user = userService.findUserByAccount((String) JwtUtil.parseToken(token).get("account"));
            article_briefs = referralMapper.articleList(name, sort, year, month, tagId, categoryId, user.getAdminStatus());
        }
        /*Page<Article_brief> page = (Page<Article_brief>) article_briefs; // 强制类型转换
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());*/

        return article_briefs;
    }

    @Override
    public List<Article> hotArticle() {
        return referralMapper.hotArticle();
    }

    @Override
    public List<Article> newArticle() {
        return referralMapper.newArticle();
    }

    @Override
    public List<Archives> listArchives() {
        return referralMapper.listArchives();
    }

    @Override
    public Article articleDetail(Integer id) {
        Article article = referralMapper.articleDetail(id);
        article.setViewCounts(article.getViewCounts()+1);
        referralMapper.updateView(id, article.getViewCounts());
        return article;
    }

    @Override
    public Article publishArticle(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        article.setAuthorId(id);
        article.setCreateDate(LocalDateTime.now());
        referralMapper.insertBody(article.getBody());
        article.setBodyId(article.getBody().getId());
        article.setCategoryId(article.getCategory().getId());
        referralMapper.insertArticle(article);
        for(Tag tag : article.getTags()) {
            referralMapper.insertArticleTag(article.getId(), tag.getId());
        }
        article.setArticleId(article.getId());
        return article;
    }

    @Override
    public void articleAgree(Integer id) {
        referralMapper.articleAgree(id);
    }

    @Override
    public void articleReject(Integer id) {
        referralMapper.articleReject(id);
    }

    @Override
    public List<Article> mineArticles() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        return referralMapper.mineArticles(id);
    }

    @Override
    public void deleteArticle(Integer id) {
        Article article = referralMapper.articleDetail(id);
        referralMapper.deleteArticleTags(id);
        referralMapper.deleteArticle(id);
        referralMapper.deleteArticleBody(article.getBody().getId());
    }
}
