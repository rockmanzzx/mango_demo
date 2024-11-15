package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.LoginLogMapper;
import com.example.mango_admin.model.LoginLog;
import com.example.mango_admin.service.LoginLogService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Transactional
    @Override
    public int writeLoginLog(String username, String ip) {
        List<LoginLog> loginLogs = loginLogMapper.findByUserNameAndStatus(username, LoginLog.STATUS_ONLINE);
        for (LoginLog loginLog : loginLogs) {
            loginLog.setStatus(LoginLog.STATUS_LOGIN);
            loginLogMapper.updateByPrimaryKey(loginLog);
        }
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(username);
        loginLog.setIp(ip);
        loginLog.setStatus(LoginLog.STATUS_LOGOUT);
        loginLogMapper.insert(loginLog);
        loginLog.setStatus(LoginLog.STATUS_ONLINE);
        loginLogMapper.insert(loginLog);
        return 0;
    }

    @Override
    public int save(LoginLog loginLog) {
        if (loginLog.getId() == null || loginLog.getId() == 0) {
            return loginLogMapper.insert(loginLog);
        }
        return loginLogMapper.updateByPrimaryKey(loginLog);
    }

    @Override
    public int delete(LoginLog loginLog) {
        return loginLogMapper.deleteByPrimaryKey(loginLog.getId());
    }

    @Override
    public int delete(List<LoginLog> list) {
        for (LoginLog loginLog : list) {
            delete(loginLog);
        }
        return list.size();
    }

    @Override
    public LoginLog findById(Long id) {
        return loginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object userName = pageRequest.getParamValue("userName");
        Object status = pageRequest.getParamValue("status");
        if (userName != null && status != null) {
            return MyBatisPageHelper.findPage(pageRequest, loginLogMapper, "findPageByUserNameAndStatus", userName, status);
        } else if (userName != null) {
            return MyBatisPageHelper.findPage(pageRequest, loginLogMapper, "findPageByUserName", userName);
        } else if (status != null) {
            return MyBatisPageHelper.findPage(pageRequest, loginLogMapper, "findPageByStatus", status);
        }
        return MyBatisPageHelper.findPage(pageRequest, loginLogMapper);
    }
}
