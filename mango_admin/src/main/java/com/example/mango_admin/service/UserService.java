package com.example.mango_admin.service;

import com.example.mango_admin.model.User;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    PageResult<User> findPage(PageRequest pageRequest);

    PageResult<User> findPageByName(PageRequest pageRequest);

}
