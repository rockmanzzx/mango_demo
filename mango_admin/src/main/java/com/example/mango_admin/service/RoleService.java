package com.example.mango_admin.service;

import com.example.mango_admin.model.Menu;
import com.example.mango_admin.model.Role;
import com.example.mango_admin.model.RoleMenu;
import org.example.common.service.CrudService;

import java.util.List;

public interface RoleService extends CrudService<Role> {

    List<Role> findAll();

    List<Menu> findRoleMenus(Long roleId);

    int saveRoleMenus(List<RoleMenu> records);

    List<Role> findByName(String name);
}
