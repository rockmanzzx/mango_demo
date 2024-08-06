package com.example.mango_admin.service;

import com.example.mango_admin.model.User;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;
import org.example.core.service.CrudService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface UserService extends CrudService<User> {
    List<User> findAll();

    User findByName(String name);

    PageResult findPage(PageRequest pageRequest);

    PageResult findPageByName(PageRequest pageRequest);

    File createUserExcleFile(PageRequest pageRequest);

    Set<String> findPermissionsByName(String name);
}
