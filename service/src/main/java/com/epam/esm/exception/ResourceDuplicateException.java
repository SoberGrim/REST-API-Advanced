package com.epam.esm.exception;

/**
 * The type Resource duplicate exception.
 */
public class ResourceDuplicateException extends RuntimeException {
    private String errorCode;
    private String message;

    /**
     * Instantiates a new Resource duplicate exception.
     *
     * @param errorCode the error code
     * @param message   the message
     */
    public ResourceDuplicateException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
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

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}