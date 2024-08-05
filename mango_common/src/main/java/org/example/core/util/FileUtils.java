package org.example.core.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {
    public static void downloadFile(HttpServletResponse response, File file, String newFileName) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(newFileName.getBytes("ISO8859-1"), "UTF-8"));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            InputStream is = new FileInputStream(file.getAbsolutePath());
            BufferedInputStream bis = new BufferedInputStream(is);
            int len = 0;
            byte[] buffer = new byte[1 * 1024 * 10];
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            bis.close();
            bos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
