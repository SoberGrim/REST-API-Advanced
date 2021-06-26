package com.epam.esm.dao.constant;

public class ErrorAttribute {
    public static final String PROPERTY_FILE_NAME = "locale/error_messages";
    public static final String GIFT_CERTIFICATE_ERROR_CODE = "1";
    public static final String INVALID_GIFT_CERTIFICATE_ERROR = "error.invalidGiftCertificate";
    public static final String INVALID_GIFT_CERTIFICATE_ID_ERROR = "error.invalidGiftCertificateId";
    public static final String TAG_ERROR_CODE = "2";
    public static final String INVALID_TAG_ID_ERROR = "error.invalidTagId";
    public static final String INVALID_TAG_NAME = "error.invalidTagName";
    public static final String TAG_DUPLICATE_ERROR = "error.tagDuplicate";
    public static final String USER_ERROR_CODE = "3";
    public static final String INVALID_USER_ID_ERROR = "error.invalidUserId";
    public static final String INVALID_ID = "error.invalidId";
    public static final String RESOURCE_NOT_FOUND_ERROR = "error.resourceNotFound";

    private ErrorAttribute() {
    }
}
