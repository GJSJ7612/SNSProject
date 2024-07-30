package org.example.snsproject.controller.blog;

import org.example.snsproject.entity.blog.Category;
import org.example.snsproject.entity.Result;
import org.example.snsproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorys/detail")
    private Result<List<Category>> getCategory() {
        List<Category> data = categoryService.getCategory();
        return Result.success(data);
    }

    @GetMapping("/categorys/detail/{id}")
    private Result<Category> getCategoryById(@PathVariable int id) {
        Category data = categoryService.getCategoryById(id);
        return Result.success(data);
    }
}
