package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.ConfigMapper;
import com.example.mango_admin.model.Config;
import com.example.mango_admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> findAll() {
        return configMapper.selectAll();
    }
}
