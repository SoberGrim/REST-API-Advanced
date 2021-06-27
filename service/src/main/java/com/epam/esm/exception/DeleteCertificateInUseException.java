package com.epam.esm.exception;

import com.epam.esm.dao.constant.ErrorAttribute;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteCertificateInUseException extends RuntimeException{
    private String errorCode;
    private String messageKey;
    private String detail;

    public DeleteCertificateInUseException(String errorCode, String messageKey, String detail) {
        this.errorCode = errorCode;
        this.messageKey = messageKey;
        this.detail = detail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLocalizedMessage(Locale locale) {
        return ResourceBundle.getBundle(ErrorAttribute.PROPERTY_FILE_NAME, locale).getString(messageKey);
    }
}
