package com.example.mango_admin.service;

import com.example.mango_admin.model.User;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;
import org.example.core.service.CrudService;

import java.io.File;
import java.util.List;

public interface UserService extends CrudService<User> {
    public List<User> findAll();

    PageResult findPage(PageRequest pageRequest);

    PageResult findPageByName(PageRequest pageRequest);

    File createUserExcleFile(PageRequest pageRequest);
}
