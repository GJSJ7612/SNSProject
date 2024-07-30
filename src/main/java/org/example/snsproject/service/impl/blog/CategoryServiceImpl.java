package org.example.snsproject.service.impl.blog;

import org.example.snsproject.entity.blog.Category;
import org.example.snsproject.mapper.blog.CategoryMapper;
import org.example.snsproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategory() {
        return categoryMapper.getCategory();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryMapper.getCategoryById(id);
    }
}
