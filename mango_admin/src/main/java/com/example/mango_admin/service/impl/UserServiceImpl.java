package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.UserMapper;
import com.example.mango_admin.model.User;
import com.example.mango_admin.service.UserService;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;
import org.example.core.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, userMapper, "selectAll");
    }
}
