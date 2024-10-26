package com.koizai.commonservice.common;

import lombok.Data;

@Data
public class BaseMessageDto {
    protected String errorCode;
    protected String errorMessage;
}
