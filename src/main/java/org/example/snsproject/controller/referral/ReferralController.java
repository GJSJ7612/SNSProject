package org.example.snsproject.controller.referral;

import org.apache.ibatis.annotations.Delete;
import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.blog.Archives;
import org.example.snsproject.entity.blog.Article;
import org.example.snsproject.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    //获取内推
    @GetMapping("/recommend")
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
        data = referralService.articleList(pageNumber,pageSize, name, sort, year, month, tagId, categoryId, token);
        return Result.success(data);
    }

    //获取最热内推
    @GetMapping("/recommend/hot")
    public Result<List<Article>> hotArticles(){
        List<Article> data = referralService.hotArticle();
        return Result.success(data);
    }

    //获取最新内推
    @GetMapping("/recommend/new")
    public Result<List<Article>> newArticles(){
        List<Article> data = referralService.newArticle();
        return Result.success(data);
    }

    //获取内推归档
    @GetMapping("/recommend/listArchives")
    public Result<List<Archives>> listArchives(){
        List<Archives> data = referralService.listArchives();
        return Result.success(data);
    }

    //查看内推详情
    @GetMapping("/recommend/view/{id}")
    public Result<Article> getArticle(@PathVariable Integer id){
        Article data = referralService.articleDetail(id);
        return Result.success(data);
    }

    //发布内推
    @PostMapping("/recommend/publish")
    public Result<Article> publishArticle(@RequestBody Article article){
        Article data = referralService.publishArticle(article);
        return Result.success(data);
    }

    //获取指定内推
    @GetMapping("/recommend/{id}")
    public Result<Article> getArticleDetail(@PathVariable Integer id){
        Article data = referralService.articleDetail(id);
        return Result.success(data);
    }

    @GetMapping("/recommend/agree/{id}")
    public Result agreeArticle(@PathVariable Integer id){
        referralService.articleAgree(id);
        return Result.success();
    }

    @GetMapping("/recommend/reject/{id}")
    public Result rejectArticle(@PathVariable Integer id){
        referralService.articleReject(id);
        return Result.success();
    }

    @GetMapping("/recommend/mine")
    public Result<List<Article>> mineArticles(){
        List<Article> data = referralService.mineArticles();
        return Result.success(data);
    }

    @DeleteMapping("/recommend/delete/{id}")
    public Result deleteArticle(@PathVariable Integer id){
        referralService.deleteArticle(id);
        return Result.success();
    }
}
