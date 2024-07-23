package com.example.mango_admin.controller;

import com.example.mango_admin.model.Config;
import com.example.mango_admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/findAll")
    public List<Config> findAll(){
        return configService.findAll();
    }
}
