package com.example.mango_admin.service;

import com.example.mango_admin.model.User;
import com.example.mango_admin.model.UserRole;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.example.common.service.CrudService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface UserService extends CrudService<User> {

    User findByName(String username);
    Set<String> findPermissions(String name);
    List<UserRole> findUserRoles(Long userId);
    File createUserExcleFile(PageRequest pageRequest);
}
