package org.example.mango_backup.controller;

import org.example.common.http.HttpResult;
import org.example.common.util.FileUtils;
import org.example.mango_backup.constants.BackupConstants;
import org.example.mango_backup.datasource.BackupDataSourceProperties;
import org.example.mango_backup.service.MysqlBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MysqlBackupController {

    @Autowired
    MysqlBackupService mysqlBackupService;
    @Autowired
    private final BackupDataSourceProperties properties;

    public MysqlBackupController(BackupDataSourceProperties backupDataSourceProperties) {
        this.properties = backupDataSourceProperties;
    }

    @GetMapping("/backup")
    public HttpResult backup() {
        String backupFolderName = BackupConstants.DEFAULT_BACKUP_NAME + "_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
        return backup(backupFolderName);
    }

    @GetMapping("restore")
    public HttpResult restore(@RequestParam String name) throws IOException {
        String host = properties.getHost();
        String username = properties.getUsername();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
        boolean success;
        try {
            success = mysqlBackupService.restore(restoreFilePath, host, username, password, database);
        } catch (Exception e) {
            return HttpResult.error(e.getMessage());
        }
        if (success) {
            return HttpResult.success();
        } else {
            return HttpResult.error();
        }
    }

    @GetMapping("/findRecords")
    public HttpResult findBackupRecords() {
        if (!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()) {
            backup(BackupConstants.DEFAULT_BACKUP_NAME);
        }
        List<Map<String, String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
        if (restoreFolderFile.exists()) {
            for (File file : restoreFolderFile.listFiles()) {
                Map<String, String> backup = new HashMap<>();
                backup.put("name", file.getName());
                backup.put("title", file.getName());
                if (BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
                    backup.put("title", "系统默认备份");
                }
                backupRecords.add(backup);
            }
        }
        backupRecords.sort((o1, o2) -> BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name")) ? -1
                :
                BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name")) ? 1 : o2.get("name").compareTo(o1.get("name")));
        return HttpResult.success(backupRecords);
    }

    @GetMapping("/delete")
    public HttpResult deleteBackupRecord(@RequestParam String name) {
        if (BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(name)) {
            return HttpResult.error("默认备份无法删除");
        }
        String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
        try {
            FileUtils.deleteFile(new File(restoreFilePath));
        } catch (Exception e) {
            return HttpResult.error(e.getMessage());
        }
        return HttpResult.success("删除成功");
    }

    private HttpResult backup(String backupFolderName) {
        String host = properties.getHost();
        String username = properties.getUsername();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String backupFolderPath = BackupConstants.BACKUP_FOLDER + backupFolderName + File.separator;
        String fileName = BackupConstants.BACKUP_FILE_NAME;
        try {
            boolean success = mysqlBackupService.backup(host, username, password, backupFolderPath, fileName, database);
            if (!success) {
                HttpResult.error("数据备份失败");
            }
        } catch (Exception e) {
            return HttpResult.error(e.getMessage());
        }
        return HttpResult.success("数据备份成功");
    }
}
