package org.example.snsproject.controller.referral;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TechController {

    @Autowired
    private TechService techService;

    //获取最热标签
    @GetMapping("/stack/hot")
    public Result<List<Tag>> hot() {
        List<Tag> data = techService.hotTag();
        return Result.success(data);
    }

    //获取全部标签
    @GetMapping("/stack/detail")
    public Result<List<Tag>> detail() {
        List<Tag> data = techService.tagDetails();
        return Result.success(data);
    }

    //获取具体标签
    @GetMapping("/stack/detail/{id}")
    public Result<Tag> detail(@PathVariable int id) {
        Tag data = techService.tagDetailsById(id);
        return Result.success(data);
    }
}
