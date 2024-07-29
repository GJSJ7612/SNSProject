package org.example.snsproject.controller.blog;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/hot")
    public Result<List<Tag>> hot() {
        List<Tag> data = tagService.hotTag();
        return Result.success(data);
    }

    @GetMapping("/tags/detail")
    public Result<List<Tag>> detail() {
        List<Tag> data = tagService.tagDetails();
        return Result.success(data);
    }

    @GetMapping("/tags/detail/{id}")
    public Result<Tag> detail(@PathVariable int id) {
        Tag data = tagService.tagDetailsById(id);
        return Result.success(data);
    }
}
