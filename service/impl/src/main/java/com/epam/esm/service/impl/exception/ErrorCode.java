package com.epam.esm.service.impl.exception;

public final class ErrorCode {

    public static final String TAG_NAME_INCORRECT = "00001";
    public static final String TAG_WITH_SUCH_NAME_ALREADY_EXIST = "00002";
    public static final String TAG_WITH_SUCH_ID_NOT_EXIST = "00003";
    public static final String TAG_WITH_SUCH_NAME_NOT_EXIST = "00004";

    public static final String GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST = "00005";
    public static final String GIFT_CERTIFICATE_NAME_INCORRECT = "00006";
    public static final String GIFT_CERTIFICATE_DESCRIPTION_INCORRECT = "00007";
    public static final String GIFT_CERTIFICATE_PRICE_INCORRECT = "00008";
    public static final String GIFT_CERTIFICATE_DURATION_INCORRECT = "00009";
    public static final String GIFT_CERTIFICATE_DATES_INCORRECT = "00010";

    private ErrorCode() {
    }
}
