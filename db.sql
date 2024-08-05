create table sys_config
(
    id               bigint auto_increment comment '编号'
        primary key,
    value            varchar(100)      not null comment '数据值',
    label            varchar(100)      not null comment '标签名',
    type             varchar(100)      not null comment '类型',
    description      varchar(100)      not null comment '描述',
    sort             decimal           not null comment '排序（升序）',
    dept_id          bigint            null comment '机构ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '系统配置表' charset = utf8mb3;

create table sys_dept
(
    id               bigint auto_increment comment '编号'
        primary key,
    name             varchar(50)       null comment '机构名称',
    parent_id        bigint            null comment '上级机构ID，一级机构为0',
    order_num        int               null comment '排序',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '机构管理' charset = utf8mb3;

create table sys_dict
(
    id               bigint auto_increment comment '编号'
        primary key,
    value            varchar(100)      not null comment '数据值',
    label            varchar(100)      not null comment '标签名',
    type             varchar(100)      not null comment '类型',
    description      varchar(100)      not null comment '描述',
    sort             decimal           not null comment '排序（升序）',
    dept_id          bigint            null comment '机构ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '字典表' charset = utf8mb3;

create table sys_log
(
    id               bigint auto_increment comment '编号'
        primary key,
    user_name        varchar(50)       null comment '用户名',
    operation        varchar(50)       null comment '用户操作',
    method           varchar(200)      null comment '请求方法',
    params           varchar(5000)     not null comment '请求参数',
    time             bigint            not null comment '执行时长（毫秒）',
    ip               varchar(64)       null comment 'IP地址',
    sort             decimal           not null comment '排序（升序）',
    dept_id          bigint            null comment '机构ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '系统操作日志' charset = utf8mb3;

create table sys_login_log
(
    id               bigint auto_increment comment '编号'
        primary key,
    user_name        varchar(50)       null comment '用户名',
    status           varchar(50)       null comment '登录状态（online：在线，登录初始状态，方便统计在线人数；login：退出登录后将online置为login；logout：退出登录）',
    ip               varchar(64)       null comment 'IP地址',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '系统登录日志' charset = utf8mb3;

create table sys_menu
(
    id               bigint auto_increment comment '编号'
        primary key,
    name             varchar(50)       null comment '菜单名称',
    parent_id        bigint            null comment '父菜单ID，一级菜单为0',
    url              varchar(200)      null comment '菜单URL，类型：1.普通页面（如用户管理，/sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL（如SQL监控，iframe:/druid/login.html, iframe:前缀会替换成服务器地址）',
    perms            varchar(500)      null comment '授权（多个用逗号分隔，如：sys:user:add:, sys:user:edit）',
    type             int               null comment '类型 0:目录 1:菜单 2:按钮',
    icon             varchar(50)       null comment '菜单图标',
    order_num        int               null comment '排序',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '菜单管理' charset = utf8mb3;

create table sys_role
(
    id               bigint auto_increment comment '编号'
        primary key,
    name             varchar(50)       null comment '角色名称',
    remark           varchar(100)      null comment '备注',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '角色管理' charset = utf8mb3;

create table sys_role_dept
(
    id               bigint auto_increment comment '编号'
        primary key,
    role_id          bigint            null comment '角色ID',
    dept_id          bigint            null comment '机构ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '角色机构' charset = utf8mb3;

create table sys_role_menu
(
    id               bigint auto_increment comment '编号'
        primary key,
    role_id          bigint            null comment '角色ID',
    menu_id          bigint            null comment '菜单ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '角色菜单' charset = utf8mb3;

create table sys_user
(
    id               bigint auto_increment comment '编号'
        primary key,
    name             varchar(50)       not null comment '用户名',
    nick_name        varchar(150)      null comment '昵称',
    avatar           varchar(150)      null comment '头像',
    password         varchar(100)      null comment '密码',
    salt             varchar(40)       null comment '加密盐',
    email            varchar(100)      null comment '邮箱',
    mobile           varchar(100)      null comment '手机号',
    status           tinyint           null comment '状态 0:禁用 1:正常',
    dept_id          bigint            null comment '机构ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常',
    constraint name
        unique (name)
)
    comment '用户管理' charset = utf8mb3;

create table sys_user_role
(
    id               bigint auto_increment comment '编号'
        primary key,
    user_id          bigint            null comment '用户ID',
    role_id          bigint            null comment '角色ID',
    create_by        varchar(50)       null comment '创建人',
    create_time      datetime          null comment '创建时间',
    last_update_by   varchar(50)       null comment '更新人',
    last_update_time datetime          null comment '更新时间',
    del_flag         tinyint default 0 null comment '是否删除 -1:已删除 0:正常'
)
    comment '用户角色' charset = utf8mb3;


