package com.epam.esm.exception;

public enum ServiceErrorInformation {
    TAG_NAME_INCORRECT(ErrorCode.ERROR_00001, ErrorMessage.ERROR_00001),
    TAG_ID_INCORRECT(ErrorCode.ERROR_00002, ErrorMessage.ERROR_00002),
    TAG_WITH_SUCH_ID_NOT_EXIST(ErrorCode.ERROR_00003, ErrorMessage.ERROR_00003),
    TAG_WITH_SUCH_NAME_NOT_EXIST(ErrorCode.ERROR_00004, ErrorMessage.ERROR_00004),
    GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST(ErrorCode.ERROR_00005, ErrorMessage.ERROR_00005),
    GIFT_CERTIFICATE_ID_INCORRECT(ErrorCode.ERROR_00006, ErrorMessage.ERROR_00006),
    GIFT_CERTIFICATE_NAME_INCORRECT(ErrorCode.ERROR_00007, ErrorMessage.ERROR_00007),
    GIFT_CERTIFICATE_DESCRIPTION_INCORRECT(ErrorCode.ERROR_00008, ErrorMessage.ERROR_00008),
    GIFT_CERTIFICATE_PRICE_INCORRECT(ErrorCode.ERROR_00009, ErrorMessage.ERROR_00009),
    GIFT_CERTIFICATE_DURATION_INCORRECT(ErrorCode.ERROR_00010, ErrorMessage.ERROR_00010),
    GIFT_CERTIFICATE_DATES_INCORRECT(ErrorCode.ERROR_00011, ErrorMessage.ERROR_00011);

    private final String errorCode;
    private final String errorMessage;

    ServiceErrorInformation(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
