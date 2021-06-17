package com.epam.esm.handler;

import com.epam.esm.exception.ExceptionResponse;
import com.epam.esm.exception.InvalidFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Invalid field exception handler.
 */
@ControllerAdvice
public class InvalidFieldExceptionHandler {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    /**
     * Handle runtime exceptions response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(InvalidFieldException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(InvalidFieldException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getLocalizedMessage(), e.getErrorCode());
        exceptionResponse.setErrorCode(status.value() + e.getErrorCode());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
