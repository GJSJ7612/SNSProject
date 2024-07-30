package org.example.snsproject.entity.blog;

import lombok.Data;

@Data
public class Tag {
    private int id;
    private String avatar;
    public String tagname;
    private int articles;
}
