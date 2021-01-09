package com.epam.esm.controller.exception;

import com.epam.esm.service.impl.exception.ServiceException;
import com.epam.esm.service.impl.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    enum HttpStatusByErrorCode {

    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse customHandle(ServiceException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("12345");
        errorResponse.setErrorCode(exception.getErrorCode());
        return errorResponse;
    }
}
