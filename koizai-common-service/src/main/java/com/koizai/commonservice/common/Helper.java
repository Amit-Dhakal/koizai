package com.koizai.commonservice.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class Helper {
    public static void validateString(String fieldName, String str) throws Exception {
        if (str == null || (str != null && str.isEmpty())) {
            throw new KoizaiException(KoizaiException.MANDATORY_VALUE, fieldName + " value is mandatory");
        }
    }

    public static boolean isValid(Object obj) {
        boolean result = true;
        if (obj == null) {
            result = false;
        } else {
            if (obj instanceof Collection) {
                Collection col = (Collection) obj;
                if (col.size() == 0) {
                    result = false;
                }
            }
        }
        return result;
    }


    public final static String DEFAULT_PATTERN = "dd/MM/yyyy";
    public final static String FILE_PATTERN = "yyyy-MM-dd";

    public static String formatDate(String pattern, LocalDate localDate) {
        if (localDate == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String result = dateTimeFormatter.format(localDate);
        return result;
    }

    public static String formatDateTime(String pattern, LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String result = dateTimeFormatter.format(localDateTime);
        return result;
    }

    public static String formatDateTime(String pattern, ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String result = dateTimeFormatter.format(zonedDateTime);
        return result;
    }

    public static String formatDate(LocalDate localDate) {
        return formatDate(DEFAULT_PATTERN, localDate);
    }
    public static String convertToName(String email){
        String result = email;
        if(email.indexOf("@")>-1){
            result = email.substring(0, email.indexOf("@"));
        }
        return result;
    }

    public static void main(String[] args) {

    }


}
