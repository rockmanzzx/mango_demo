package org.example.core.exception;

import org.example.core.http.HttpResult;
import org.example.core.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e) {
        return HttpResult.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
