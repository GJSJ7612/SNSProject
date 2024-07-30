package org.example.snsproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Application {
    private long id; // 申请操作的ID
    private int uid; // 申请者ID
    private String avatar; // 申请者头像
    private String nickname; // 申请者昵称
    private String email; // 申请者邮箱
    private String phone; // 申请者电话
    private long pid; // 任务的ID
    private int bid; // 报价
    private LocalDate time; // 预计完成时间
    private String resume; // 申请者简历
}
