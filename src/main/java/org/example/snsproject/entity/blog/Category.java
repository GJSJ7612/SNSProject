package org.example.snsproject.entity.blog;

import lombok.Data;

@Data
public class Category {
    private int id;
    private String avatar;
    private String categoryname;
    private String description;
    private int articles;
}
