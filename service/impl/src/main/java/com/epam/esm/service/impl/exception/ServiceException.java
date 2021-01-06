package com.epam.esm.service.impl.exception;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(Throwable exp) {
        super(exp);
    }
    public ServiceException(String errorCode) {
        super(errorCode);

    }
    public ServiceException(String errorCode, Throwable exp) {
        super(errorCode, exp);
    }
}
