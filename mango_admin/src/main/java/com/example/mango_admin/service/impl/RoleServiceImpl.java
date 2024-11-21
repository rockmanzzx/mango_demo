package com.example.mango_admin.service.impl;

import com.example.mango_admin.constant.SysConstants;
import com.example.mango_admin.mapper.MenuMapper;
import com.example.mango_admin.mapper.RoleMapper;
import com.example.mango_admin.mapper.RoleMenuMapper;
import com.example.mango_admin.model.Menu;
import com.example.mango_admin.model.Role;
import com.example.mango_admin.model.RoleMenu;
import com.example.mango_admin.service.RoleService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public List<Menu> findRoleMenus(Long roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
            return menuMapper.selectAll();
        }
        return menuMapper.findRoleMenus(roleId);
    }

    @Override
    public int saveRoleMenus(List<RoleMenu> records) {
        if (records == null || records.isEmpty()) {
            return 0;
        }
        Long roleId = records.get(0).getRoleId();
        roleMenuMapper.deleteByRoleId(roleId);
        for (RoleMenu record : records) {
            roleMenuMapper.insert(record);
        }
        return records.size();
    }

    @Override
    public List<Role> findByName(String name) {
        return roleMapper.findByName(name);
    }

    @Override
    public int save(Role role) {
        if (role.getId() == null || role.getId() == 0) {
            return roleMapper.insert(role);
        }
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int delete(Role role) {
        return roleMapper.deleteByPrimaryKey(role.getId());
    }

    @Override
    public int delete(List<Role> list) {
        for (Role role : list) {
            delete(role);
        }
        return list.size();
    }

    @Override
    public Role findById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("name");
        if (label != null) {
            return MyBatisPageHelper.findPage(pageRequest, roleMapper, "findPageByName", label);
        }
        return MyBatisPageHelper.findPage(pageRequest, roleMapper);
    }
}
