package org.example.snsproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProjectDetail {
    private long pid; // 任务ID
    private int uid; // 发布任务的用户ID
    private String avatar; // 用户头像的图片路径
    private String nickname; // 用户昵称
    private int category; // 任务类别
    private String title; // 任务标题
    private String info; // 任务详情（完整版）
    private Date deadline; // 任务投标截止时间
    private int priceLower; // 任务价格下限
    private int priceUpper; // 任务价格上限
    private String tel; // 联系方式
    private int applied; // 无权限则为0，待申请则为1，已申请则为2
    private List<Integer> applicantIDs;
    private List<String> applicantAvatars;
    private List<String> applicantNames;
    // 若当前用户无权限查看该项目的申请者，则上面三者为null
    private int receiverIndex; // 接收者在前面List中的下标，无权限则为-2，未选择接收者则为-1
}
