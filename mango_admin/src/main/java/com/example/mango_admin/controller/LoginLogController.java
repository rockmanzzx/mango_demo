package com.example.mango_admin.controller;

import com.example.mango_admin.model.LoginLog;
import com.example.mango_admin.service.LoginLogService;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("loginlog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @PreAuthorize("hasAuthority('sys:loginlog:view')")
    @PostMapping("findPage")
    public HttpResult findPage(PageRequest pageRequest) {
        return HttpResult.success(loginLogService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:loginlog:delete')")
    @PostMapping("delete")
    public HttpResult delete(List<LoginLog> loginLogs) {
        return HttpResult.success(loginLogService.delete(loginLogs));
    }
}
