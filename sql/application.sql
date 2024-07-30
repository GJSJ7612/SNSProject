create table application
(
    id     bigint auto_increment comment '申请操作的ID'
        primary key,
    uid    int          not null comment '申请者ID',
    pid    bigint       not null comment '任务ID',
    bid    int          null comment '报价',
    time   date         null comment '预计完成时间',
    resume varchar(127) null comment '申请者简历'
);