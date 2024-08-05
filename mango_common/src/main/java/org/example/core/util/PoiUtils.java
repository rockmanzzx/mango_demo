package org.example.core.util;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import java.io.*;

public class PoiUtils {

    public static File createExcelFile(Workbook workbook, String fileName) {
        OutputStream outputStream = null;
        File file = null;
        try {
            file = File.createTempFile(fileName, ".xlsx");
            outputStream = new FileOutputStream(file.getAbsoluteFile());
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(outputStream);
        }
        return file;
    }

}
