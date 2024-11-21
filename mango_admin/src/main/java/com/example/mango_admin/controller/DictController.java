package com.example.mango_admin.controller;

import com.example.mango_admin.model.Dict;
import com.example.mango_admin.service.DictService;
import org.example.common.http.HttpResult;
import org.example.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    @PostMapping("save")
    public HttpResult save(Dict dict) {
        return HttpResult.success(dictService.save(dict));
    }

    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping("delete")
    public HttpResult delete(List<Dict> dicts) {
        return HttpResult.success(dictService.delete(dicts));
    }

    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping("findPage")
    public HttpResult findPage(PageRequest pageRequest) {
        return HttpResult.success(dictService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping("findByLabel")
    public HttpResult findByLabel(String label) {
        return HttpResult.success(dictService.findByLabel(label));
    }
}
