package org.example.snsproject.service;

import org.example.snsproject.entity.blog.Category;

import java.util.List;

public interface CategoryService {

    //获取全部分类
    List<Category> getCategory();

    //跟据id获取详细分类
    Category getCategoryById(int id);
}
