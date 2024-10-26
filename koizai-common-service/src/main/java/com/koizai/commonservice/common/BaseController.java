package com.koizai.commonservice.common;

import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected ResponseEntity doResponse(BaseMessageDto result) {
        if (result.getErrorMessage() == null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
