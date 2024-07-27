create table project
(
    pid         bigint auto_increment comment '任务ID'
        primary key,
    uid         int          not null comment '发布任务的用户ID',
    type        int          not null comment '任务类别',
    title       varchar(31)  null comment '任务标题',
    info        varchar(511) null comment '任务详情',
    deadline    date         null comment '任务投标截止时间',
    price_lower int          null comment '任务价格下限',
    price_upper int          null comment '任务价格上限',
    tel         varchar(15)  null comment '联系方式',
    receiver    int          null comment '最终接收项目者的ID',
    create_time datetime     not null comment '创建时间'
);
