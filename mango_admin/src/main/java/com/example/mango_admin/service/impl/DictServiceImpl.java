package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.DictMapper;
import com.example.mango_admin.model.Dict;
import com.example.mango_admin.service.DictService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public int save(Dict dict) {
        if (dict.getId() == null || dict.getId() == 0) {
            return dictMapper.insert(dict);
        }
        return dictMapper.updateByPrimaryKey(dict);
    }

    @Override
    public int delete(Dict dict) {
        return dictMapper.deleteByPrimaryKey(dict.getId());
    }

    @Override
    public int delete(List<Dict> list) {
        for (Dict dict : list) {
            delete(dict);
        }
        return list.size();
    }

    @Override
    public Dict findById(Long id) {
        return dictMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("label");
        if (label != null) {
            return MyBatisPageHelper.findPage(pageRequest, dictMapper, "findPageByLabel", label);
        }
        return MyBatisPageHelper.findPage(pageRequest, dictMapper);
    }

    @Override
    public List<Dict> findByLabel(String label) {
        return dictMapper.findByLabel(label);
    }
}
