package com.koizai.commonservice.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CommonService implements Constants {
    private Resource jspFile;

    public CommonService(@Value("classpath:index.jsp") Resource jspFile) {
        this.jspFile = jspFile;
        copyJspFile();
    }

    private String getFilesDirectory() {
        String dirPath = APP_HOME + SLASH + WEB_APPS + SLASH + KOIZAI_FOLDER;
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();

        }

        return dirPath;
    }

    private void copyJspFile() {
        String dirPath = getFilesDirectory();
        File targetJspFile = new File(dirPath + SLASH + "index.jsp");
        if (targetJspFile == null || !targetJspFile.exists()) {
            try {
                Files.copy(Paths.get(jspFile.getFile().getPath()), new FileOutputStream(targetJspFile));
            } catch (Exception ex) {

            }

        }
    }
}
