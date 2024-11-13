package com.example.mango_admin.controller;

import com.example.mango_admin.model.Config;
import com.example.mango_admin.service.ConfigService;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @PreAuthorize("hasAuthority('sys:config:add') AND hasAnyAuthority('sys:config:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody Config config) {
        return HttpResult.success(configService.save(config));
    }

    @PreAuthorize("hasAuthority('sys:config:delete')")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<Config> configs) {
        return HttpResult.success(configService.delete(configs));
    }

    // findPage
    @PreAuthorize("hasAuthority('sys:config:view')")
    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.success(configService.findPage(pageRequest));
    }

    // findByLabel
    @PreAuthorize("hasAuthority('sys:config:view')")
    @GetMapping("/findByLabel")
    public HttpResult findByLabel(@RequestParam String label) {
        return HttpResult.success(configService.findByLabel(label));
    }
}
