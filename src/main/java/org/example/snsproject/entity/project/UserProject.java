package org.example.snsproject.entity.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserProject {
    private long pid; // 任务ID
    private int uid; // 发布任务的用户ID
    private String avatar; // 用户头像的图片路径
    private String nickname; // 用户昵称
    private int category; // 任务类别
    private String title; // 任务标题
    private String info; // 任务详情，只取前32个字符
    private Date deadline; // 任务投标截止时间
    private int priceLower; // 任务价格下限
    private int priceUpper; // 任务价格上限
    // 概览界面不需要联系方式
}
