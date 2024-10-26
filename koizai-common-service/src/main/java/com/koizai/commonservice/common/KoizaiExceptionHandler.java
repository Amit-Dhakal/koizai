package com.koizai.commonservice.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KoizaiExceptionHandler {

    public static void handleException(BaseMessageDto result, Exception ex) {
        log.error(ex.getMessage(), ex);
        if (ex instanceof KoizaiException) {
            KoizaiException mex = (KoizaiException) ex;
            result.setErrorCode(mex.getCode());
        } else if (ex instanceof KoizaiRuntimeException) {
            KoizaiRuntimeException mex = (KoizaiRuntimeException) ex;
            result.setErrorCode(mex.getCode());
        }
        result.setErrorMessage(ex.getMessage());
    }
}
