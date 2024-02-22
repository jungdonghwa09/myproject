package com.donghwa.commons.exceptions;

import org.modelmapper.internal.Errors;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class CommonException extends RuntimeException {
    private HttpStatus status;
    private Map<String, List<String>> messages;

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

   /* private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;*/
    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CommonException(Errors errors, HttpStatus status) {
        this.status = status;

        /* 커맨드 객체 검증 실패 -> Map<String, List<String>> -> messages */

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}