create table if not exists user
(
    id          int auto_increment comment '用户ID'
        primary key,
    account     varchar(255) not null comment '用户名',
    password    varchar(255) not null comment '密码',
    avatar      varchar(255) null comment '用户头像',
    email       varchar(255) null comment '邮箱',
    phone       varchar(20)  null,
    nickname    varchar(255) null,
    status      int          not null,
    create_time date         null,
    update_time date         null
)
    comment '用户';

