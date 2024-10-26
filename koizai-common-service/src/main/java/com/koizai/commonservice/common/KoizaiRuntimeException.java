package com.koizai.commonservice.common;


import lombok.Data;

/**
 * Runtime exception specific for this project
 *
 * @author erik.malik@devstack.com.au
 */
@Data
public class KoizaiRuntimeException extends RuntimeException implements ErrorCodes {
    private String code = UNKNOWN_EXCEPTION;

    public KoizaiRuntimeException(String message) {
        super(message);
    }

    public KoizaiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KoizaiRuntimeException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public KoizaiRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }


}
