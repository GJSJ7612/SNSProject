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
    private int applied; // 可查看详情为0，可申请为1，已申请但尚未确定接收者为2，已申请且申请成功为3，已申请但申请失败为4， 其他为-1
    private List<Application> applications; // 申请详情
    // 若当前用户无权限查看该项目的申请者，则上面四者为null
    private int receiver; // 接收者的用户ID，未确定接收者为-1（默认值）
    // 若当前用户无权获知接收者，即便已确定接收者，也返回-1
}
