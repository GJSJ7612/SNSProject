package org.example.snsproject.service.impl.blog;

import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.blog.TagMapper;
import org.example.snsproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> hotTag() {
        return tagMapper.hotTag();
    }

    @Override
    public List<Tag> tagDetails() {
        return tagMapper.tagDetails();
    }

    @Override
    public Tag tagDetailsById(int id) {
        return tagMapper.tagDetailsById(id);
    }
}
