package org.example.snsproject.controller.blog;
import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.entity.Result;
import org.example.snsproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public Result<List<Article>> articles(
            @RequestHeader(value = "Oauth-Token", required = false)String token,
            Integer pageNumber, Integer pageSize,
            String  name, String sort,
            Integer year, Integer month,
            Integer tagId, Integer categoryId) {
        List<Article> data;
        if(year == null || month == null){
            year = -1;
            month = -1;
        }
        if(tagId == null){
           tagId = -1;
        }
        if(categoryId == null){
            categoryId = -1;
        }
        data = articleService.articleList(pageNumber,pageSize, name, sort, year, month, tagId, categoryId, token);
        return Result.success(data);
    }

    @GetMapping("/articles/hot")
    public Result<List<Article>> hotArticles(){
        List<Article> data = articleService.hotArticle();
        return Result.success(data);
    }

    @GetMapping("/articles/new")
    public Result<List<Article>> newArticles(){
        List<Article> data = articleService.newArticle();
        return Result.success(data);
    }

    @GetMapping("/articles/listArchives")
    public Result<List<Archives>> listArchives(){
        List<Archives> data = articleService.listArchives();
        return Result.success(data);
    }

    @GetMapping("/articles/view/{id}")
    public Result<Article> getArticle(@PathVariable Integer id){
        Article data = articleService.articleDetail(id);
        return Result.success(data);
    }

    @PostMapping("/articles/publish")
    public Result<Article> publishArticle(@RequestBody Article article){
        Article data = articleService.publishArticle(article);
        return Result.success(data);
    }

    @GetMapping("/articles/{id}")
    public Result<Article> getArticleDetail(@PathVariable Integer id){
        Article data = articleService.articleDetail(id);
        return Result.success(data);
    }

    @GetMapping("/articles/agree/{id}")
    public Result agreeArticle(@PathVariable Integer id){
        articleService.articleAgree(id);
        return Result.success();
    }

    @GetMapping("/articles/reject/{id}")
    public Result rejectArticle(@PathVariable Integer id){
        articleService.articleReject(id);
        return Result.success();
    }
}
