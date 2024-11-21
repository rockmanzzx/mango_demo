package com.example.mango_admin.controller;

import com.example.mango_admin.constant.SysConstants;
import com.example.mango_admin.mapper.RoleMapper;
import com.example.mango_admin.model.Role;
import com.example.mango_admin.model.RoleMenu;
import com.example.mango_admin.service.RoleService;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @PreAuthorize("hasAuthority('sys:role:add') AND hasAnyAuthority('sys:role:edit')")
    @PostMapping("/save")
    public HttpResult save(Role record) {
        Role role = roleService.findById(record.getId());
        if (role != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        // 新增角色
        if ((record.getId() == null || record.getId() == 0) && !roleService.findByName(record.getName()).isEmpty()) {
            return HttpResult.error("角色名已存在!");
        }
        return HttpResult.success(roleService.save(record));
    }

    // delete
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping("/delete")
    public HttpResult delete(List<Role> records) {
        return HttpResult.success(roleService.delete(records));
    }

    // findPage
    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping("/findPage")
    public HttpResult findPage(PageRequest pageRequest) {
        return HttpResult.success(roleService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping("/findAll")
    public HttpResult findAll() {
        return HttpResult.success(roleService.findAll());
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping("/findRoleMenus")
    public HttpResult findRoleMenus(@RequestParam Long roleId) {
        return HttpResult.success(roleService.findRoleMenus(roleId));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/saveRoleMenus")
    public HttpResult saveRoleMenus(List<RoleMenu> records) {
        for (RoleMenu roleMenu : records) {
            Role role = roleMapper.selectByPrimaryKey(roleMenu.getRoleId());
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.success(roleService.saveRoleMenus(records));
    }

}
