create table if not exists article_body
(
    id           int auto_increment comment '博文主体ID'
        primary key,
    content      longtext null comment '博文主体内容',
    content_html longtext null comment '博文html内容'
)
    comment '博文主体';

create table if not exists category
(
    id            int auto_increment comment '分类ID'
        primary key,
    avater        varchar(255) null comment '分类图标',
    category_name varchar(255) null comment '分类名称',
    description   longtext     null comment '分类评论'
)
    comment '分类表';

create table if not exists tag
(
    id       int auto_increment comment '标签ID'
        primary key,
    avater   varchar(255) null comment '标签图标',
    tag_name varchar(255) null comment '标签名称'
)
    comment '标签表';

create table if not exists user
(
    uid         int auto_increment comment '用户ID'
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

create table if not exists article_brief
(
    id             int auto_increment comment '博文ID'
        primary key,
    comment_counts int default 0 not null comment '博文评论数',
    createTime     datetime      not null,
    summary        varchar(255)  null comment '博文概要',
    title          varchar(255)  null comment '博文标题',
    view_count     int default 0 not null comment '博文观看数',
    author_id      int           null comment '博文作者id',
    body_id        int           null comment '博文主题ID',
    category_id    int           null comment '博文分类ID',
    constraint `article_brief_ article_body_id_fk`
        foreign key (body_id) references article_body (id),
    constraint article_brief_category_id_fk
        foreign key (category_id) references category (id),
    constraint article_brief_user_uid_fk
        foreign key (author_id) references user (uid)
)
    comment '博文概要';

create table if not exists article_tag_link
(
    article_id int null comment '博文ID',
    tag_id     int null comment '标签ID',
    constraint article_tag_link_article_brief_id_fk
        foreign key (article_id) references article_brief (id),
    constraint article_tag_link_tag_id_fk
        foreign key (tag_id) references tag (id)
)
    comment '博文与标签关联表';

create table if not exists comment
(
    id          int auto_increment comment '评论ID'
        primary key,
    content     text          null comment '评论内容',
    create_date datetime      null comment '标签创建时间',
    article_id  int           null comment '评论所属文章ID',
    author_id   int           null comment '评论发布者ID',
    parent_id   int           null comment '父评论ID',
    to_uid      int           null comment '父评论用户ID',
    level       int default 1 null comment '评论层级',
    constraint comment_article_brief_id_fk
        foreign key (article_id) references article_brief (id),
    constraint comment_comment_id_fk
        foreign key (parent_id) references comment_article (id),
    constraint comment_user_uid_fk
        foreign key (author_id) references user (uid),
    constraint comment_user_uid_fk_2
        foreign key (to_uid) references user (uid)
)
    comment '评论表';


