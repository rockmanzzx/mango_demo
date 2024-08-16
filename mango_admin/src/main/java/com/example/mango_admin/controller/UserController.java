package com.example.mango_admin.controller;

import com.example.mango_admin.model.User;
import com.example.mango_admin.service.UserService;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.example.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody User user) {
        return HttpResult.success(userService.save(user));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody User user) {
        return HttpResult.success(userService.delete(user));
    }

    @PostMapping("/findPage")
    public HttpResult listUsers(@RequestBody PageRequest pageRequest) {
        return HttpResult.success(userService.findPage(pageRequest));
    }

    @PostMapping("/findPageByName")
    public HttpResult findPageByName(@RequestBody PageRequest pageRequest) {
        return HttpResult.success(userService.findPageByName(pageRequest));
    }

    @PostMapping("/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse response) {
        File file = userService.createUserExcleFile(pageRequest);
        FileUtils.downloadFile(response, file, file.getName());
    }
}
