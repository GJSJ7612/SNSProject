package org.example.snsproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Project {
    private long pid; // 任务ID
    private int uid; // 发布任务的用户ID
    private int type; // 任务类别
    private String title; // 任务标题
    private String info; // 任务详情
    private Date deadline; // 任务投标截止时间
    private int priceLower; // 任务价格下限
    private int priceUpper; // 任务价格上限
    private String tel; // 联系方式
    private List<Integer> applicants; // 申请者的ID
    private int receiver; // 最终接收项目者的ID
    private LocalDateTime createTime; // 创建时间
}
