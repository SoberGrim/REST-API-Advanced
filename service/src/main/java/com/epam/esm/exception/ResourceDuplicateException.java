package com.epam.esm.exception;

import com.epam.esm.dao.constant.ErrorAttribute;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Resource duplicate exception.
 */
public class ResourceDuplicateException extends RuntimeException {
    private String errorCode;
    private String messageKey;
    private String detail;

    /**
     * Instantiates a new Resource duplicate exception.
     *
     * @param errorCode  the error code
     * @param messageKey the message key
     * @param detail     the detail
     */
    public ResourceDuplicateException(String errorCode, String messageKey, String detail) {
        this.errorCode = errorCode;
        this.messageKey = messageKey;
        this.detail = detail;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets message key.
     *
     * @return the message key
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Sets message key.
     *
     * @param messageKey the message key
     */
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    /**
     * Gets detail.
     *
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets detail.
     *
     * @param detail the detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Gets localized message.
     *
     * @param locale the locale
     * @return the localized message
     */
    public String getLocalizedMessage(Locale locale) {
        return ResourceBundle.getBundle(ErrorAttribute.PROPERTY_FILE_NAME, locale).getString(messageKey);
    }
}