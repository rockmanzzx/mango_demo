package com.example.mango_admin.controller;

import com.example.mango_admin.model.Menu;
import com.example.mango_admin.service.MenuService;
import org.example.common.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAnyAuthority('sys:menu:edit')")
    @PostMapping("/save")
    public HttpResult save(Menu menu) {
        return HttpResult.success(menuService.save(menu));
    }

    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping("/delete")
    public HttpResult delete(List<Menu> records) {
        return HttpResult.success(menuService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping("/findNavTree")
    public HttpResult findNavTree() {
        return HttpResult.success(menuService.findTree(null, 1));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping("/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.success(menuService.findTree(null, 0));
    }

}
