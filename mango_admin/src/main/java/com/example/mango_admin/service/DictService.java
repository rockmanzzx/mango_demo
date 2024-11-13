package com.example.mango_admin.service;

import com.example.mango_admin.model.Dict;
import org.example.common.service.CrudService;

import java.util.List;

public interface DictService extends CrudService<Dict> {
    List<Dict> findByLabel(String label);
}
