package com.epam.esm.handler;

import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ExceptionResponse;
import com.epam.esm.util.ExceptionMessageLocale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DaoExceptionHandler {
    private static final String SPACE_SYMBOL = " ";
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler(DaoException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(DaoException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getLocalizedMessage(
                ExceptionMessageLocale.getCurrent()) + SPACE_SYMBOL + e.getDetail(), e.getErrorCode());
        exceptionResponse.setErrorCode(status.value() + e.getErrorCode());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
