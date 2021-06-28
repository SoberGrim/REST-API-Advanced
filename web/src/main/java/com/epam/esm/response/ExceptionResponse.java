package com.epam.esm.response;

/**
 * The type Exception response.
 */
public class ExceptionResponse {
    private String message;
    private String errorCode;

    /**
     * Instantiates a new Exception response.
     */
    public ExceptionResponse() {
    }

    /**
     * Instantiates a new Exception response.
     *
     * @param message   the message
     * @param errorCode the error code
     */
    public ExceptionResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
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
}
