package org.example.snsproject.entity.activity;

import lombok.Data;

@Data
public class Activity_archives {
    private int commentCounts;
    private int count;
    private int month;
    private String summary="";
    private String title="";
    private int viewCounts=0;
    private int weight;
    private int year;
}
