package org.example.snsproject.controller.referral;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.blog.Category;
import org.example.snsproject.service.CategoryService;
import org.example.snsproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //
    @GetMapping("/industry/detail")
    private Result<List<Category>> getCategory() {
        List<Category> data = companyService.getCategory();
        return Result.success(data);
    }

    @GetMapping("/industry/detail/{id}")
    private Result<Category> getCategoryById(@PathVariable int id) {
        Category data = companyService.getCategoryById(id);
        return Result.success(data);
    }
}
