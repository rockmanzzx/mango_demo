package org.example.mango_backup.util;

import org.apache.commons.math3.geometry.partitioning.BSPTree;

import java.io.*;

public class MysqlBackupRestoreUtils {

    public static boolean backup(String host, String username, String password, String backupFolderPath, String fileName, String database) throws Exception{

        File backupFolderFile = new File(backupFolderPath);
        if (!backupFolderFile.exists()) {
            backupFolderFile.mkdirs();
        }

        if (!backupFolderPath.endsWith(File.separator) && !backupFolderPath.endsWith("/")) {
            backupFolderPath += File.separator;
        }

        String backupFilePath = backupFolderPath + fileName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump --opt ")
                .append(" --add-drop-database ")
                .append(" --add-drop-table ");
        stringBuilder.append(" -h ")
                .append(host)
                .append(" -u ")
                .append(username);
//                .append(" -p ")
//                .append(password);
        stringBuilder.append(" --result-file=")
                .append(backupFilePath)
                .append(" --default-character-set=utf8 ")
                .append(database);

        Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (process.waitFor() == 0) {
            System.out.println("数据已备份到 " + backupFilePath);
            return true;
        } else {
            System.err.println("备份失败，进程退出代码: " + process.exitValue());
            return false;
        }
    }

    public static boolean restore(String restoreFilePath, String host, String username, String password, String database) throws Exception{
        File restoreFile = new File(restoreFilePath);
        if (restoreFile.isDirectory()) {
            for (File file : restoreFile.listFiles()) {
                if (file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFile = file.getAbsoluteFile();
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql -h ")
                .append(host)
                .append(" -u ")
                .append(username);
//                .append(" -p")
//                .append(password);
        stringBuilder.append(" ")
                .append(database);
//                .append(" < ")
//                .append(restoreFilePath);

        try {
            ProcessBuilder processBuilder = getProcessBuilder(stringBuilder.toString());
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (InputStream inputStream = new FileInputStream(restoreFile)) {
                OutputStream outputStream = process.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                outputStream.close();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            if (process.waitFor() == 0) {
                System.out.println("数据已从 " + restoreFilePath + " 导入到数据库中");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if (os.toLowerCase().startsWith("win")) {
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = {shell, c, command};
        return cmd;
    }

    private static ProcessBuilder getProcessBuilder(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if (os.toLowerCase().startsWith("win")) {
            shell = "cmd";
            c = "/c";
        }
        return new ProcessBuilder(shell, c, command);
    }

}
