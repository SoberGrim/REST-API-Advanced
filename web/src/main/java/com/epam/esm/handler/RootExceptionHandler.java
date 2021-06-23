package com.epam.esm.handler;

import com.epam.esm.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RootExceptionHandler {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getLocalizedMessage(),
                String.valueOf(status.value()));
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
