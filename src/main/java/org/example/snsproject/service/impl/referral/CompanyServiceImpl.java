package org.example.snsproject.service.impl.referral;

import org.example.snsproject.entity.blog.Category;
import org.example.snsproject.mapper.referral.CompanyMapper;
import org.example.snsproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper CompanyMapper;

    @Override
    public List<Category> getCategory() {
        return CompanyMapper.getCategory();
    }

    @Override
    public Category getCategoryById(int id) {
        return CompanyMapper.getCategoryById(id);
    }

}
