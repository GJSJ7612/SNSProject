package org.example.snsproject.service.impl.referral;

import org.example.snsproject.entity.blog.Tag;
import org.example.snsproject.mapper.referral.TechMapper;
import org.example.snsproject.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechServiceImpl implements TechService {

    @Autowired
    private TechMapper techMapper;

    @Override
    public List<Tag> hotTag() {
        return techMapper.hotTag();
    }

    @Override
    public List<Tag> tagDetails() {
        return techMapper.tagDetails();
    }

    @Override
    public Tag tagDetailsById(int id) {
        return techMapper.tagDetailsById(id);
    }
}
