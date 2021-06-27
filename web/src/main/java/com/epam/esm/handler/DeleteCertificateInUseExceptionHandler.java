package com.epam.esm.handler;

import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.exception.DeleteCertificateInUseException;
import com.epam.esm.response.ExceptionResponse;
import com.epam.esm.util.MessageLocale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DeleteCertificateInUseExceptionHandler {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(DeleteCertificateInUseException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(DeleteCertificateInUseException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getLocalizedMessage(
                MessageLocale.getCurrent()) + Symbol.SPACE + e.getDetail(), e.getErrorCode());
        exceptionResponse.setErrorCode(status.value() + e.getErrorCode());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
