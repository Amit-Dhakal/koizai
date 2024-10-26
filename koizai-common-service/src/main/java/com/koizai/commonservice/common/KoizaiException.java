package com.koizai.commonservice.common;

import lombok.Data;

@Data
public class KoizaiException extends Exception implements ErrorCodes {
    private String code = UNKNOWN_EXCEPTION;

    public KoizaiException(String code, String message) {
        super(message);
        this.code = code;
    }
}
