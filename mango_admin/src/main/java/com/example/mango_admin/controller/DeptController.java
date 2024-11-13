package com.example.mango_admin.controller;

import com.example.mango_admin.model.Dept;
import com.example.mango_admin.service.DeptService;
import org.example.common.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    @PreAuthorize("hasAuthority('sys:dept:add') AND hasAnyAuthority('sys:dept:edit')")
    @PostMapping("save")
    public HttpResult save(Dept dept) {
        return HttpResult.success(deptService.save(dept));
    }

    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping("delete")
    public HttpResult delete(List<Dept> depts) {
        return HttpResult.success(deptService.delete(depts));
    }

    @PreAuthorize("hasAuthority('sys:dept:view')")
    @PostMapping("findTree")
    public HttpResult findTree() {
        return HttpResult.success(deptService.findTree());
    }

}
