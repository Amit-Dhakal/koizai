package com.koizai.commonservice.common;


import java.math.BigDecimal;

public interface Constants {
    String UPDATE_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss O";
    int DEFAULT_ROUNDING = BigDecimal.ROUND_HALF_UP;
    int DEFAULT_SCALE = 5;
    int DISPLAY_SCALE = 2;
    BigDecimal ZERO = BigDecimal.ZERO;
    BigDecimal HUNDRED = BigDecimal.valueOf(100);
    BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);

    String USER_HOME = System.getProperty("user.home");
    String APP_HOME = System.getProperty("user.dir");
    String WEB_APPS = "webapps";
    String SLASH = System.getProperty("file.separator");
    String LINE_SEPARATOR = System.getProperty("line.separator");
    String COMMA = ",";
    String UNDERSCORE = "_";
    String DASH = "-";
    String SEMICOLON = ";";
    String PIPE = "|";
    String DEST_FOLDER_KEY = "email.destination.path";
    String KOIZAI_FOLDER = "koizai_files";
    String KOIZAI_SHAREPOINT = "sharepoint";

} // End of interface Constants.
