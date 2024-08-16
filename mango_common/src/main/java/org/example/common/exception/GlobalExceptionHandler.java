package org.example.common.exception;

import org.example.common.http.HttpResult;
import org.example.common.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e) {
        return HttpResult.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
