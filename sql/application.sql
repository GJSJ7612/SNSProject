create table application
(
    id  bigint auto_increment comment '申请操作的ID'
        primary key,
    uid int    not null comment '申请者ID',
    pid bigint not null comment '任务ID'
);
