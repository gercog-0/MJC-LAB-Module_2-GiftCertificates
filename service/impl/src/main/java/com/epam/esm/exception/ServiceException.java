package com.epam.esm.exception;

public class ServiceException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public ServiceException() {
    }

    public ServiceException(Throwable exp) {
        super(exp);
    }

    public ServiceException(ServiceErrorInformation serviceErrorInformation, Throwable exp) {
        super(serviceErrorInformation.getErrorCode(), exp);
    }

    public ServiceException(ServiceErrorInformation serviceErrorInformation) {
        super(serviceErrorInformation.getErrorCode());
        this.errorCode = serviceErrorInformation.getErrorCode();
        this.errorMessage = serviceErrorInformation.getErrorMessage();
    }
}
