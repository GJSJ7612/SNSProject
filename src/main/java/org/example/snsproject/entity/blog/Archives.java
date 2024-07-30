package org.example.snsproject.entity.blog;

import lombok.Data;

@Data
public class Archives {
    private int commentCounts;
    private int count;
    private int month;
    private String summary="";
    private String title="";
    private int viewCounts=0;
    private int weight;
    private int year;
}
