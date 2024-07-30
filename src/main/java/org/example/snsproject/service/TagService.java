package org.example.snsproject.service;

import org.example.snsproject.entity.blog.Tag;

import java.util.List;

public interface TagService {

    //获取最热标签
    List<Tag> hotTag();

    //获取全部标签
    List<Tag> tagDetails();

    //通过id获取某个特定标签
    Tag tagDetailsById(int id);
}
