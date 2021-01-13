package com.epam.esm.service.api.exception;

public class ServiceException extends RuntimeException {

    private String errorCode;
    private String errorReason;

    public ServiceException(Throwable exp) {
        super(exp);
    }

    public ServiceException(String errorCode, String errorReason) {
        super();
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }
}
