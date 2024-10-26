package com.koizai.commonservice.sharepoint.util;

import java.io.File;

import com.koizai.commonservice.common.Constants;

public class ConstantUtil implements Constants {

    public static final String clientId = "3dd046e3-65dd-41e1-a266-f17f42535a64";
    public static final String clientSecret = "8a~8Q~V.L.ggxihTBlwgkjCYGqryIJmw4Ebn5aK_";
    public static final String tenantId = "02b87979-d129-41ba-a3df-f7e17e86c8ea";
    public static final String domain = "malabarai";
    public static final String shared_folder = "Shared Documents";
    public static final String site = "/sites/sharepointteam";
    
    
    public static String getFilesDirectory() {
        String dirPath = USER_HOME + SLASH + KOIZAI_SHAREPOINT + SLASH + shared_folder;
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();

        }

        return dirPath;
    }

}