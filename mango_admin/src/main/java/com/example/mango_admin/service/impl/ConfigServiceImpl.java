package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.ConfigMapper;
import com.example.mango_admin.model.Config;
import com.example.mango_admin.service.ConfigService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public int save(Config config) {
        if (config.getId() == null || config.getId() == 0) {
            return configMapper.insert(config);
        }
        return configMapper.updateByPrimaryKey(config);
    }

    @Override
    public int delete(Config config) {
        return configMapper.deleteByPrimaryKey(config.getId());
    }

    @Override
    public int delete(List<Config> list) {
        for (Config config : list) {
            delete(config);
        }
        return list.size();
    }

    @Override
    public Config findById(Long id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Config> findAll() {
        return configMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("label");
        if (label == null || label.toString().trim().isEmpty()) {
            return MyBatisPageHelper.findPage(pageRequest, configMapper);
        }
        return MyBatisPageHelper.findPage(pageRequest, configMapper, "findPage", label);
    }

    @Override
    public List<Config> findByLabel(String label) {
        return configMapper.findByLabel(label);
    }


}
