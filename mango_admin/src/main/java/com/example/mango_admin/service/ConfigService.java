package com.example.mango_admin.service;

import com.example.mango_admin.model.Config;
import org.example.common.service.CrudService;

import java.util.List;

public interface ConfigService extends CrudService<Config> {
    public List<Config> findAll();

    List<Config> findByLabel(String label);
}
