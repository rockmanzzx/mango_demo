-- 插入 sys_config 表测试数据
INSERT INTO sys_config (value, label, type, description, sort, dept_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('value1', 'label1', 'type1', 'description1', 1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    ('value2', 'label2', 'type2', 'description2', 2, 2, 'admin', NOW(), 'admin', NOW(), 0),
    ('value3', 'label3', 'type3', 'description3', 3, 3, 'admin', NOW(), 'admin', NOW(), 0),
    ('value4', 'label4', 'type4', 'description4', 4, 4, 'admin', NOW(), 'admin', NOW(), 0),
    ('value5', 'label5', 'type5', 'description5', 5, 5, 'admin', NOW(), 'admin', NOW(), 0),
    ('value6', 'label6', 'type6', 'description6', 6, 6, 'admin', NOW(), 'admin', NOW(), 0),
    ('value7', 'label7', 'type7', 'description7', 7, 7, 'admin', NOW(), 'admin', NOW(), 0),
    ('value8', 'label8', 'type8', 'description8', 8, 8, 'admin', NOW(), 'admin', NOW(), 0),
    ('value9', 'label9', 'type9', 'description9', 9, 9, 'admin', NOW(), 'admin', NOW(), 0),
    ('value10', 'label10', 'type10', 'description10', 10, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_dept 表测试数据
INSERT INTO sys_dept (name, parent_id, order_num, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('Dept1', 0, 1, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept2', 1, 2, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept3', 1, 3, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept4', 2, 4, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept5', 2, 5, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept6', 3, 6, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept7', 3, 7, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept8', 4, 8, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept9', 4, 9, 'admin', NOW(), 'admin', NOW(), 0),
    ('Dept10', 5, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_dict 表测试数据
INSERT INTO sys_dict (value, label, type, description, sort, dept_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('dict_value1', 'dict_label1', 'dict_type1', 'dict_description1', 1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value2', 'dict_label2', 'dict_type2', 'dict_description2', 2, 2, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value3', 'dict_label3', 'dict_type3', 'dict_description3', 3, 3, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value4', 'dict_label4', 'dict_type4', 'dict_description4', 4, 4, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value5', 'dict_label5', 'dict_type5', 'dict_description5', 5, 5, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value6', 'dict_label6', 'dict_type6', 'dict_description6', 6, 6, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value7', 'dict_label7', 'dict_type7', 'dict_description7', 7, 7, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value8', 'dict_label8', 'dict_type8', 'dict_description8', 8, 8, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value9', 'dict_label9', 'dict_type9', 'dict_description9', 9, 9, 'admin', NOW(), 'admin', NOW(), 0),
    ('dict_value10', 'dict_label10', 'dict_type10', 'dict_description10', 10, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_log 表测试数据
INSERT INTO sys_log (user_name, operation, method, params, time, ip, sort, dept_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('admin', 'login', 'POST', '{}', 100, '192.168.1.1', 1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    ('user1', 'view', 'GET', '{}', 200, '192.168.1.2', 2, 2, 'admin', NOW(), 'admin', NOW(), 0),
    ('user2', 'edit', 'PUT', '{}', 150, '192.168.1.3', 3, 3, 'admin', NOW(), 'admin', NOW(), 0),
    ('user3', 'delete', 'DELETE', '{}', 120, '192.168.1.4', 4, 4, 'admin', NOW(), 'admin', NOW(), 0),
    ('user4', 'create', 'POST', '{}', 300, '192.168.1.5', 5, 5, 'admin', NOW(), 'admin', NOW(), 0),
    ('user5', 'update', 'PATCH', '{}', 180, '192.168.1.6', 6, 6, 'admin', NOW(), 'admin', NOW(), 0),
    ('user6', 'select', 'GET', '{}', 250, '192.168.1.7', 7, 7, 'admin', NOW(), 'admin', NOW(), 0),
    ('user7', 'export', 'GET', '{}', 210, '192.168.1.8', 8, 8, 'admin', NOW(), 'admin', NOW(), 0),
    ('user8', 'import', 'POST', '{}', 190, '192.168.1.9', 9, 9, 'admin', NOW(), 'admin', NOW(), 0),
    ('user9', 'login', 'POST', '{}', 140, '192.168.1.10', 10, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_login_log 表测试数据
INSERT INTO sys_login_log (user_name, status, ip, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('admin', 'online', '192.168.1.1', 'admin', NOW(), 'admin', NOW(), 0),
    ('user1', 'online', '192.168.1.2', 'admin', NOW(), 'admin', NOW(), 0),
    ('user2', 'online', '192.168.1.3', 'admin', NOW(), 'admin', NOW(), 0),
    ('user3', 'offline', '192.168.1.4', 'admin', NOW(), 'admin', NOW(), 0),
    ('user4', 'offline', '192.168.1.5', 'admin', NOW(), 'admin', NOW(), 0),
    ('user5', 'online', '192.168.1.6', 'admin', NOW(), 'admin', NOW(), 0),
    ('user6', 'offline', '192.168.1.7', 'admin', NOW(), 'admin', NOW(), 0),
    ('user7', 'online', '192.168.1.8', 'admin', NOW(), 'admin', NOW(), 0),
    ('user8', 'offline', '192.168.1.9', 'admin', NOW(), 'admin', NOW(), 0),
    ('user9', 'online', '192.168.1.10', 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_menu 表测试数据
INSERT INTO sys_menu (name, parent_id, url, perms, type, icon, order_num, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('Menu1', 0, '/menu1', 'sys:menu:view', 1, 'icon1', 1, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu2', 0, '/menu2', 'sys:menu:add', 1, 'icon2', 2, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu3', 1, '/menu3', 'sys:menu:edit', 1, 'icon3', 3, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu4', 1, '/menu4', 'sys:menu:delete', 1, 'icon4', 4, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu5', 2, '/menu5', 'sys:menu:select', 1, 'icon5', 5, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu6', 2, '/menu6', 'sys:menu:update', 1, 'icon6', 6, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu7', 3, '/menu7', 'sys:menu:import', 1, 'icon7', 7, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu8', 3, '/menu8', 'sys:menu:export', 1, 'icon8', 8, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu9', 4, '/menu9', 'sys:menu:login', 1, 'icon9', 9, 'admin', NOW(), 'admin', NOW(), 0),
    ('Menu10', 4, '/menu10', 'sys:menu:logout', 1, 'icon10', 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_role 表测试数据
INSERT INTO sys_role (name, remark, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('Role1', 'Remark1', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role2', 'Remark2', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role3', 'Remark3', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role4', 'Remark4', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role5', 'Remark5', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role6', 'Remark6', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role7', 'Remark7', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role8', 'Remark8', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role9', 'Remark9', 'admin', NOW(), 'admin', NOW(), 0),
    ('Role10', 'Remark10', 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_role_dept 表测试数据
INSERT INTO sys_role_dept (role_id, dept_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    (1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    (1, 2, 'admin', NOW(), 'admin', NOW(), 0),
    (2, 3, 'admin', NOW(), 'admin', NOW(), 0),
    (2, 4, 'admin', NOW(), 'admin', NOW(), 0),
    (3, 5, 'admin', NOW(), 'admin', NOW(), 0),
    (3, 6, 'admin', NOW(), 'admin', NOW(), 0),
    (4, 7, 'admin', NOW(), 'admin', NOW(), 0),
    (4, 8, 'admin', NOW(), 'admin', NOW(), 0),
    (5, 9, 'admin', NOW(), 'admin', NOW(), 0),
    (5, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_role_menu 表测试数据
INSERT INTO sys_role_menu (role_id, menu_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    (1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    (1, 2, 'admin', NOW(), 'admin', NOW(), 0),
    (2, 3, 'admin', NOW(), 'admin', NOW(), 0),
    (2, 4, 'admin', NOW(), 'admin', NOW(), 0),
    (3, 5, 'admin', NOW(), 'admin', NOW(), 0),
    (3, 6, 'admin', NOW(), 'admin', NOW(), 0),
    (4, 7, 'admin', NOW(), 'admin', NOW(), 0),
    (4, 8, 'admin', NOW(), 'admin', NOW(), 0),
    (5, 9, 'admin', NOW(), 'admin', NOW(), 0),
    (5, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_user 表测试数据
INSERT INTO sys_user (name, nick_name, avatar, password, salt, email, mobile, status, dept_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    ('user1', 'nickname1', 'avatar1', 'password1', 'salt1', 'user1@example.com', '1234567890', 1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    ('user2', 'nickname2', 'avatar2', 'password2', 'salt2', 'user2@example.com', '1234567891', 1, 2, 'admin', NOW(), 'admin', NOW(), 0),
    ('user3', 'nickname3', 'avatar3', 'password3', 'salt3', 'user3@example.com', '1234567892', 1, 3, 'admin', NOW(), 'admin', NOW(), 0),
    ('user4', 'nickname4', 'avatar4', 'password4', 'salt4', 'user4@example.com', '1234567893', 1, 4, 'admin', NOW(), 'admin', NOW(), 0),
    ('user5', 'nickname5', 'avatar5', 'password5', 'salt5', 'user5@example.com', '1234567894', 1, 5, 'admin', NOW(), 'admin', NOW(), 0),
    ('user6', 'nickname6', 'avatar6', 'password6', 'salt6', 'user6@example.com', '1234567895', 1, 6, 'admin', NOW(), 'admin', NOW(), 0),
    ('user7', 'nickname7', 'avatar7', 'password7', 'salt7', 'user7@example.com', '1234567896', 1, 7, 'admin', NOW(), 'admin', NOW(), 0),
    ('user8', 'nickname8', 'avatar8', 'password8', 'salt8', 'user8@example.com', '1234567897', 1, 8, 'admin', NOW(), 'admin', NOW(), 0),
    ('user9', 'nickname9', 'avatar9', 'password9', 'salt9', 'user9@example.com', '1234567898', 1, 9, 'admin', NOW(), 'admin', NOW(), 0),
    ('user10', 'nickname10', 'avatar10', 'password10', 'salt10', 'user10@example.com', '1234567899', 1, 10, 'admin', NOW(), 'admin', NOW(), 0);

-- 插入 sys_user_role 表测试数据
INSERT INTO sys_user_role (user_id, role_id, create_by, create_time, last_update_by, last_update_time, del_flag)
VALUES
    (1, 1, 'admin', NOW(), 'admin', NOW(), 0),
    (2, 2, 'admin', NOW(), 'admin', NOW(), 0),
    (3, 3, 'admin', NOW(), 'admin', NOW(), 0),
    (4, 4, 'admin', NOW(), 'admin', NOW(), 0),
    (5, 5, 'admin', NOW(), 'admin', NOW(), 0),
    (6, 6, 'admin', NOW(), 'admin', NOW(), 0),
    (7, 7, 'admin', NOW(), 'admin', NOW(), 0),
    (8, 8, 'admin', NOW(), 'admin', NOW(), 0),
    (9, 9, 'admin', NOW(), 'admin', NOW(), 0),
    (10, 10, 'admin', NOW(), 'admin', NOW(), 0);
