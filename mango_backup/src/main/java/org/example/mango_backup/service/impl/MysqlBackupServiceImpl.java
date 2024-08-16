package org.example.mango_backup.service.impl;

import org.example.mango_backup.service.MysqlBackupService;
import org.example.mango_backup.util.MysqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {

    @Override
    public boolean backup(String host, String username, String password, String backupFolderPath, String filename, String database) throws Exception {
        return MysqlBackupRestoreUtils.backup(host, username, password, backupFolderPath, filename, database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String username, String password, String database) throws Exception {
        return MysqlBackupRestoreUtils.restore(restoreFilePath, host, username, password, database);
    }
}
