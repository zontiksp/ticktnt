package org.example.mynewplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogZipper {

    public static void zipLogFile(File dataFolder) {
        File logFile = new File(dataFolder, "tnt-log.txt");
        if (!logFile.exists()) return;

        File zipFolder = new File(dataFolder, "logs");
        if (!zipFolder.exists()) {
            zipFolder.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        File zipFile = new File(zipFolder, "tnt-log-" + timeStamp + ".zip");

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(logFile)) {

            ZipEntry zipEntry = new ZipEntry(logFile.getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();

            logFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
