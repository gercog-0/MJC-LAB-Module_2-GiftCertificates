package com.epam.esm.service.api.exception;

/**
 * The type Service exception.
 */
public class ServiceException extends RuntimeException {

    private String errorCode;
    private String errorReason;

    /**
     * Instantiates a new Service exception.
     *
     * @param exp the exp
     */
    public ServiceException(Throwable exp) {
        super(exp);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param errorCode   the error code
     * @param errorReason the error reason
     */
    public ServiceException(String errorCode, String errorReason) {
        super();
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param errorCode the error code
     */
    public ServiceException(String errorCode){
        super();
        this.errorCode = errorCode;
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
     * Gets error reason.
     *
     * @return the error reason
     */
    public String getErrorReason() {
        return errorReason;
    }
}
