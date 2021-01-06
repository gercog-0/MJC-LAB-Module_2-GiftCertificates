package com.epam.esm.service.impl.exception;

public final class ErrorCode {

    public static final String TAG_NAME_INCORRECT_ERROR = "00001";
    public static final String TAG_ID_INCORRECT_ERROR = "00002";
    public static final String TAG_WITH_SUCH_ID_NOT_EXIST_ERROR = "00003";
    public static final String TAG_WITH_SUCH_NAME_NOT_EXIST_ERROR = "00004";

    public static final String GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST_ERROR = "00005";
    public static final String GIFT_CERTIFICATE_ID_INCORRECT_ERROR = "00006";
    public static final String GIFT_CERTIFICATE_NAME_INCORRECT_ERROR = "00007";
    public static final String GIFT_CERTIFICATE_DESCRIPTION_INCORRECT_ERROR = "00008";
    public static final String GIFT_CERTIFICATE_PRICE_INCORRECT_ERROR = "00009";
    public static final String GIFT_CERTIFICATE_DURATION_INCORRECT_ERROR = "00010";
    public static final String GIFT_CERTIFICATE_DATES_INCORRECT_ERROR = "00011";

    private ErrorCode() {
    }
}
