package com.example.mango_admin.controller;

import com.example.mango_admin.constant.SysConstants;
import com.example.mango_admin.model.User;
import com.example.mango_admin.service.UserService;
import com.example.mango_admin.util.PasswordUtils;
import com.example.mango_admin.util.SecurityUtils;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.example.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody User record) {
        User user = userService.findById(record.getId());
        if (user != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        if (record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                if (userService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                if (!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.success(userService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<User> records) {
        for (User record : records) {
            User user = userService.findById(record.getId());
            if (user != null && SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.success(userService.delete(records));
    }

    // findByName
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/findByName")
    public HttpResult findByName(@RequestParam String name) {
        return HttpResult.success(userService.findByName(name));
    }

    // findPermissions
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/findPermissions")
    public HttpResult findPermissions(@RequestParam String name) {
        return HttpResult.success(userService.findPermissions(name));
    }

    // findUserRoles
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/findUserRoles")
    public HttpResult findUserRoles(@RequestParam Long userId) {
        return HttpResult.success(userService.findUserRoles(userId));
    }

    // findPage
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.success(userService.findPage(pageRequest));
    }

    // exportUserExcelFile
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/exportUserExcelFile")
    public HttpResult exportUserExcelFile(@RequestBody PageRequest pageRequest) {
        return HttpResult.success(userService.createUserExcleFile(pageRequest));
    }

    // updatePassword
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PostMapping("/updatePassword")
    public HttpResult updatePassword(@RequestParam String password, @RequestParam String newPassword) {
        User user = userService.findByName(SecurityUtils.getUsername());
        if (user == null) {
            HttpResult.error("用户不存在!");
        }
        if (SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
            return HttpResult.error("超级管理员不允许修改!");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return HttpResult.error("原密码不正确!");
        }
        user.setPassword(PasswordUtils.encode(newPassword, user.getSalt()));
        return HttpResult.success(userService.save(user));
    }
}
