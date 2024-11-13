package com.example.mango_admin.service;

import com.example.mango_admin.model.User;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.example.common.service.CrudService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface UserService extends CrudService<User> {

    User findByName(String name);

    File createUserExcleFile(PageRequest pageRequest);

    Set<String> findPermissions(String name);
}
