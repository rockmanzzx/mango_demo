package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.LogMapper;
import com.example.mango_admin.model.Log;
import com.example.mango_admin.service.LogService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int save(Log log) {
        if (log.getId() == null || log.getId() == 0) {
            return logMapper.insert(log);
        }
        return logMapper.updateByPrimaryKey(log);
    }

    @Override
    public int delete(Log log) {
        return logMapper.deleteByPrimaryKey(log.getId());
    }

    @Override
    public int delete(List<Log> list) {
        for (Log log : list) {
            delete(log);
        }
        return list.size();
    }

    @Override
    public Log findById(Long id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MyBatisPageHelper.findPage(pageRequest, logMapper);
    }
}
