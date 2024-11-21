package com.example.mango_admin.controller;

import com.example.mango_admin.model.Log;
import com.example.mango_admin.service.LogService;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @PreAuthorize("hasAuthority('sys:log:view')")
    @PostMapping("/findPage")
    public HttpResult findPage(PageRequest pageRequest) {
        return HttpResult.success(logService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:log:delete')")
    @PostMapping("/delete")
    public HttpResult delete(List<Log> logs) {
        return HttpResult.success(logService.delete(logs));
    }

}
