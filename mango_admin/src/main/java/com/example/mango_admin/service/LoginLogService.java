package com.example.mango_admin.service;

import com.example.mango_admin.model.LoginLog;
import org.example.common.service.CrudService;

public interface LoginLogService extends CrudService<LoginLog> {
    int writeLoginLog(String username, String ip);
}
